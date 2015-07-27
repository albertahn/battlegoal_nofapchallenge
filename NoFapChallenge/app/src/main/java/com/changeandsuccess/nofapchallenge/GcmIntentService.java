package com.changeandsuccess.nofapchallenge;

/* http://techlovejump.in/2013/11/android-push-notification-using-google-cloud-messaging-gcm-php-google-play-service-library/
 * techlovejump.in
 * tutorial link
 * 
 *  */

import android.app.Activity;
import android.app.AlertDialog;
import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.changeandsuccess.nofapchallenge.gcm_stuff.RecieverActions;
import com.google.android.gms.gcm.GoogleCloudMessaging;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

public class GcmIntentService extends IntentService{
   private Context context = this;  //this;

   // private  RecieverActions recieverActions = new RecieverActions(context);

    //public static final int NOTIFICATION_ID = 1;//(int)Math.random()*10;
    private static final int NOTIFICATION_ID = 1;//(int)Math.random()*10;
    //private NotificationManager mNotificationManager;
   // NotificationCompat.Builder builder;
    private static final String TAG = "Tanggoal Beta";

    public GcmIntentService() {
        super("GcmIntentService");
        // TODO Auto-generated constructor stub
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        // TODO Auto-generated method stub
        Bundle extras = intent.getExtras();
        String msg = intent.getStringExtra("message");
        String otherguyname = intent.getStringExtra("otherguyname");
        String notify_type = intent.getStringExtra("notify_type");
        String otherguy_pic = intent.getStringExtra("otherguy_pic");
        String otherguy_index = intent.getStringExtra("otherguy_index");

        GoogleCloudMessaging gcm = GoogleCloudMessaging.getInstance(this);
        String messageType = gcm.getMessageType(intent);
        if (!extras.isEmpty()) {

            if (GoogleCloudMessaging.
                    MESSAGE_TYPE_SEND_ERROR.equals(messageType)) {

                sendNotification("Send error: " + extras.toString(), "","","","");
            } else if (GoogleCloudMessaging.
                    MESSAGE_TYPE_DELETED.equals(messageType)) {
                sendNotification("Deleted messages on server: " +
                        extras.toString(), "","","","");
                // If it's a regular GCM message, do some work.
            } else if (GoogleCloudMessaging.
                    MESSAGE_TYPE_MESSAGE.equals(messageType)) {
                // This loop represents the service doing some work.
                for (int i=0; i<5; i++) {
                    Log.i(TAG, "Working... " + (i+1)
                            + "/5 @ " + SystemClock.elapsedRealtime());
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {

                    }
                }
                Log.i(TAG, "Completed work @ " + SystemClock.elapsedRealtime());
                // Post notification of received message.
                //sendNotification("Received: " + extras.toString());

                Log.e("notify_type: ", ""+ notify_type);

                if(msg !=null){
                    sendNotification(msg, otherguyname, otherguy_index, otherguy_pic, notify_type);
                    Log.i(TAG, "Received: " + extras.toString());
                }//end if

            }
        }
        GcmBroadcastReceiver.completeWakefulIntent(intent);
    }

    //set the notification
    private void sendNotification(String msg, String otherguyname, String otherguy_index, String otherguy_pic, String notify_type) {

        Log.e("gotmessage",""+msg);

        if(notify_type.equals("private_message")){

            new RecieverActions(context).privateMessage(msg, otherguyname, otherguy_index, otherguy_pic);

        }else if (notify_type.equals("group_chat")){

            new RecieverActions(context).groupChat( msg, otherguyname, otherguy_index, otherguy_pic);

        }else if (notify_type.equals("battle")){

            new RecieverActions(context).battle( msg, otherguyname, otherguy_index, otherguy_pic);
        }


    }//end sendmessage



//alert dialog

   /* public void alertDialog(String msg, String otherguyname, String otherguy_index, String otherguy_pic){


        AlertDialog alertDialog = new AlertDialog.Builder(this).create();

        // Setting Dialog Title
        alertDialog.setTitle(otherguyname);

        // Setting Dialog Message
        alertDialog.setMessage(msg);

        ImageLoader imageloader = ImageLoader.getInstance();
        if(imageloader.isInited()==false) {

            imageloader.init(ImageLoaderConfiguration.createDefault(this));

        }//end if

        Bitmap bitmap = (Bitmap)imageloader.loadImageSync("http://tanggoal.com/public/uploads/members_pic/"+otherguy_pic);

        BitmapDrawable icon = new BitmapDrawable(this.getResources(), bitmap);
            // Setting alert dialog icon
            alertDialog.setIcon(icon);



        // Showing Alert Message
        alertDialog.show();

    }*/


}