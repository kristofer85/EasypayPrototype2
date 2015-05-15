package com.example.notandi.easypayprototype;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.res.Configuration;
import android.nfc.NfcAdapter;
import android.nfc.Tag;
import android.nfc.tech.IsoDep;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.widget.DrawerLayout;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.github.devnied.emvnfccard.model.EmvCard;
import com.github.devnied.emvnfccard.parser.EmvParser;
import com.github.devnied.emvnfccard.utils.AtrUtils;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.util.Collection;

import de.keyboardsurfer.android.widget.crouton.Crouton;
import fr.devnied.bitlib.BytesUtils;

//import com.github.devnied.emvnfccard.adapter.MenuDrawerAdapter;
//import com.github.devnied.emvnfccard.fragment.AboutFragment;
//import com.github.devnied.emvnfccard.fragment.BillingFragment;
//import com.github.devnied.emvnfccard.fragment.ConfigurationFragment;
//import com.github.devnied.emvnfccard.fragment.IRefreshable;
//import com.github.devnied.emvnfccard.fragment.ViewPagerFragment;
//import com.github.devnied.emvnfccard.provider.Provider;
//import com.github.devnied.emvnfccard.utils.ConstantUtils;
//import com.github.devnied.emvnfccard.utils.NFCUtils;
//import com.github.devnied.emvnfccard.utils.SimpleAsyncTask;
//import com.readystatesoftware.systembartint.SystemBarTintManager;

/**
 * Main Activity
 *
 * @author MILLAU Julien
 *
 */
@SuppressLint("InlinedApi")
public class HomeActivity extends FragmentActivity
{


    /**
     * Nfc utils
     */
    private NFCUtils mNfcUtils;

    /**
     * Waiting Dialog
     */
    private ProgressDialog mDialog;

    /**
     * Alert dialog
     */
    //private AlertDialog mAlertDialog;

    /**
     * Drawer layout
     */
    private DrawerLayout mDrawerLayout;
    /**
     * ListView drawer
     */
    private ListView mDrawerListView;
    /**
     * Action bar drawer toggle
     */
    //private ActionBarDrawerToggle mActionBarDrawerToggle;

    /**
     * Menu adapter
     */
    //private MenuDrawerAdapter mMenuAdapter;

    /**
     * IsoDep provider
     */
    private Provider mProvider = new Provider();

    /**
     * Emv card
     */
    private EmvCard mReadCard;

    /**
     * Reference for refreshable content
     */
    //private WeakReference<IRefreshable> mRefreshableContent;

    /**
     * last selected Menu
     */
    private int mLastSelectedMenu = -1;

    /**
     * Tint manager
     */
    //private SystemBarTintManager tintManager;

    /**
     * Last Ats
     */
    private byte[] lastAts;
    public String user;
    public String Amount;
    private TextView lblAmount;
    private TextView lblUser;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_activity);
        user = getIntent().getStringExtra("User_Name");
        Amount = String.valueOf(getIntent().getIntExtra("Amount",0));
        lblAmount = (TextView) findViewById(R.id.lblAmount);
        lblUser = (TextView) findViewById(R.id.AmountHome);
        lblAmount.setText(getString(R.string.amount) + " "+ Amount);
        lblUser.setText(user);

        // init NfcUtils
        mNfcUtils = new NFCUtils(this);




        Button btnManualCard = (Button) findViewById(R.id.btnManualCard2);
        btnManualCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(v.getContext(), EnterCardManualy.class);
                intent.putExtra("Amount", Amount);
                intent.putExtra("Address", "Strandgata 1337");
                startActivityForResult(intent, 0);
            }
        });
        // Read card on launch
        if (getIntent().getAction() == NfcAdapter.ACTION_TECH_DISCOVERED) {
            onNewIntent(getIntent());
        }
    }

    /**
     * Method used to back to home screen
     */

    @Override
    protected void onResume() {
        mNfcUtils.enableDispatch();

        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mNfcUtils.disableDispatch();
    }

    @Override
    protected void onNewIntent(final Intent intent) {
        super.onNewIntent(intent);
        final Tag mTag = intent.getParcelableExtra(NfcAdapter.EXTRA_TAG);
        if (mTag != null) {

            new SimpleAsyncTask() {

                /**
                 * Tag comm
                 */
                private IsoDep mTagcomm;

                /**
                 * Emv Card
                 */
                private EmvCard mCard;

                /**
                 * Boolean to indicate exception
                 */
                private boolean mException;

                @Override
                protected void onPreExecute() {
                    super.onPreExecute();

                    //backToHomeScreen();
                    mProvider.getLog().setLength(0);
                    // Show dialog
                    if (mDialog == null) {
                        mDialog = ProgressDialog.show(HomeActivity.this, getString(R.string.card_reading),
                                getString(R.string.card_reading_desc), true, false);
                    } else {
                        mDialog.show();
                    }
                }

                @Override
                protected void doInBackground() {

                    mTagcomm = IsoDep.get(mTag);
                    if (mTagcomm == null)
                    {
                        return;
                    }
                    mException = false;

                    try {
                        mReadCard = null;
                        // Open connection
                        mTagcomm.connect();
                        lastAts = getAts(mTagcomm);

                        mProvider.setmTagCom(mTagcomm);

                        EmvParser parser = new EmvParser(mProvider, true);
                        mCard = parser.readEmvCard();
                        if (mCard != null) {
                            mCard.setAtrDescription(extractAtsDescription(lastAts));
                        }

                    } catch (IOException e) {
                        mException = true;
                    } finally {
                        // close tagcomm
                        IOUtils.closeQuietly(mTagcomm);
                    }
                }

                @Override
                protected void onPostExecute(final Object result)
                {
                    // close dialog
                    if (mDialog != null) {
                        mDialog.cancel();
                    }

                    if (!mException)
                    {
                        if (mCard != null)
                        {
                            if (StringUtils.isNotBlank(mCard.getCardNumber()))
                            {
                                String test = mCard.getCardNumber();
                                String test2 = mCard.getExpireDate().toString();
                                Intent intent = new Intent(HomeActivity.this, PaymentResult.class);
                                intent.putExtra("User_Name", user);
                                intent.putExtra("Amount", Amount);
                                intent.putExtra("Address", "Strandgata 1337");
                                intent.putExtra("PaymentConfirmed", true);
                                startActivity(intent);
                                mReadCard = mCard;
                            }
                        }
                    }
                }

            }.execute();
        }

    }

    private byte[] getAts(final IsoDep pIso) {
        byte[] ret = null;
        if (pIso.isConnected()) {
            // Extract ATS from NFC-A
            ret = pIso.getHistoricalBytes();
            if (ret == null) {
                // Extract ATS from NFC-B
                ret = pIso.getHiLayerResponse();
            }
        }
        return ret;
    }

    public Collection<String> extractAtsDescription(final byte[] pAts) {
        return AtrUtils.getDescriptionFromAts(BytesUtils.bytesToString(pAts));
    }

    @Override
    protected void onDestroy() {
        Crouton.cancelAllCroutons();
        super.onDestroy();
    }

    @Override
    protected void onPostCreate(final Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
    }

    @Override
    public void onConfigurationChanged(final Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onOptionsItemSelected(final MenuItem item)
    {
        /*
        if (mActionBarDrawerToggle.onOptionsItemSelected(item))
        {
            Crouton.cancelAllCroutons();
            return true;
        }
        */
        return super.onOptionsItemSelected(item);

    }

    public StringBuffer getLog() {
        return mProvider.getLog();
    }

    public EmvCard getCard()
    {
        return mReadCard;
    }

    public void clear() {
        mReadCard = null;
        mProvider.getLog().setLength(0);

    }

    public byte[] getLastAts() {
        return lastAts;
    }

}