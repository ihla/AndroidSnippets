    /**
     * Respond to "Start" button by requesting activity recognition
     * updates.
     * @param view The view that triggered this method.
     */
    public void onStartUpdates(View view) {

        // Check for Google Play services
        if (!servicesConnected()) {

            return;
        }

        /*
         * Set the request type. If a connection error occurs, and Google Play services can
         * handle it, then onActivityResult will use the request type to retry the request
         */
        mRequestType = ActivityUtils.REQUEST_TYPE.ADD;

        // Pass the update request to the requester object
        mDetectionRequester.requestUpdates();
    }

    /**
     * Respond to "Stop" button by canceling updates.
     * @param view The view that triggered this method.
     */
    public void onStopUpdates(View view) {

        // Check for Google Play services
        if (!servicesConnected()) {

            return;
        }

        /*
         * if stop button is pressed prior to start button,
         * pending intent object is not created yet!!!
         */
        if (mDetectionRequester.getRequestPendingIntent() == null) {
            Log.d(ActivityUtils.APPTAG, "pending intent null");
            return;
		}
        /*
         * Set the request type. If a connection error occurs, and Google Play services can
         * handle it, then onActivityResult will use the request type to retry the request
         */
        mRequestType = ActivityUtils.REQUEST_TYPE.REMOVE;

        // Pass the remove request to the remover object
        mDetectionRemover.removeUpdates(mDetectionRequester.getRequestPendingIntent());

        /*
         * Cancel the PendingIntent. Even if the removal request fails, canceling the PendingIntent
         * will stop the updates.
         */
        mDetectionRequester.getRequestPendingIntent().cancel();
    }
