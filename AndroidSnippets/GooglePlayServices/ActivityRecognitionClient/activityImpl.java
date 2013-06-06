import com.example.android.activityrecognition.DetectionRemover;
import com.example.android.activityrecognition.DetectionRequester;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Set the main layout
        setContentView(R.layout.activity_main);

        // Set the broadcast receiver intent filer
        mBroadcastManager = LocalBroadcastManager.getInstance(this);

        // Create a new Intent filter for the broadcast receiver
        mBroadcastFilter = new IntentFilter(ActivityUtils.ACTION_REFRESH_STATUS_LIST);
        mBroadcastFilter.addCategory(ActivityUtils.CATEGORY_LOCATION_SERVICES);

        // Get detection requester and remover objects
        mDetectionRequester = new DetectionRequester(this);
        mDetectionRemover = new DetectionRemover(this);

    }

    @Override
    protected void onResume() {
        super.onResume();

        // Register the broadcast receiver
        mBroadcastManager.registerReceiver(
                updateReceiver,
                mBroadcastFilter);
    }
    
    
    /**
     * Broadcast receiver that receives activity update intents
     * This receiver is local only. It can't read broadcast Intents from other apps.
     */
    BroadcastReceiver updateReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {

        	
        	
        }
    };

    /*
     * Handle results returned to this Activity by other Activities started with
     * startActivityForResult(). In particular, the method onConnectionFailed() in
     * DetectionRemover and DetectionRequester may call startResolutionForResult() to
     * start an Activity that handles Google Play services problems. The result of this
     * call returns here, to onActivityResult.
     * 
     * see the impl of start&stopUpdates.java
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {

        // Choose what to do based on the request code
        switch (requestCode) {

            // If the request code matches the code sent in onConnectionFailed
            case ActivityUtils.CONNECTION_FAILURE_RESOLUTION_REQUEST :

                switch (resultCode) {
                    // If Google Play services resolved the problem
                    case Activity.RESULT_OK:

                        // If the request was to start activity recognition updates
                        if (ActivityUtils.REQUEST_TYPE.ADD == mRequestType) {

                            // Restart the process of requesting activity recognition updates
                            mDetectionRequester.requestUpdates();

                        // If the request was to remove activity recognition updates
                        } else if (ActivityUtils.REQUEST_TYPE.REMOVE == mRequestType ){

                                /*
                                 * Restart the removal of all activity recognition updates for the 
                                 * PendingIntent.
                                 */
                                mDetectionRemover.removeUpdates(
                                    mDetectionRequester.getRequestPendingIntent());

                        }
                    break;

                    // If any other result was returned by Google Play services
                    default:

                        // Report that Google Play services was unable to resolve the problem.
                        Log.d(ActivityUtils.APPTAG, getString(R.string.no_resolution));
                }

            // If any other request code was received
            default:
               // Report that this Activity received an unknown requestCode
               Log.d(ActivityUtils.APPTAG,
                       getString(R.string.unknown_activity_request_code, requestCode));

               break;
        }
    }
