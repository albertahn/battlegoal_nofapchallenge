package com.changeandsuccess.nofapchallenge.gcm_stuff;

import android.app.Activity;
import android.app.IntentService;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.app.NotificationCompat;
import android.view.Gravity;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.changeandsuccess.nofapchallenge.GcmIntentService;
import com.changeandsuccess.nofapchallenge.MainActivity;
import com.changeandsuccess.nofapchallenge.MessageInside;
import com.changeandsuccess.nofapchallenge.R;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

/**
 * Created by albert on 10/1/14.
 */
public class RecieverActions extends GcmIntentService {

    public static final int NOTIFICATION_ID = 1;

   Context context=null;//= getBaseContext();
    private NotificationManager mNotificationManager=null;

   private Bitmap bitmap =null;
    private ImageLoader imageloader=null;
    private NotificationCompat.Builder mBuilder=null;
   private Intent myintent=null;
    private PendingIntent contentIntent=null;

    public RecieverActions(Context context){

        this.context = context;

    }

    public void privateMessage(String msg, String otherguyname, String otherguy_index, String otherguy_pic){


        mNotificationManager = (NotificationManager)
                context.getSystemService(Context.NOTIFICATION_SERVICE);

//when click notification open MessgeInside
        myintent = new Intent(context, MessageInside.class);

        myintent.putExtra("other_guy_index", otherguy_index);
        myintent.putExtra("otherguyname", otherguyname);
         contentIntent = PendingIntent.getActivity(context, 0,
                myintent, PendingIntent.FLAG_UPDATE_CURRENT);

        //otherguy_pic

        imageloader = ImageLoader.getInstance();
        if(imageloader.isInited()==false) {

            imageloader.init(ImageLoaderConfiguration.createDefault(context));

        }//end if

         bitmap = (Bitmap)imageloader.loadImageSync("http://tanggoal.com/public/uploads/members_pic/"+otherguy_pic);

        mBuilder =
                new NotificationCompat.Builder(context)

                        .setSmallIcon(R.drawable.ic_launcher)
                        .setLargeIcon(bitmap)
                        .setAutoCancel(true)
                                //set image
                                //set title
                        .setContentTitle(otherguyname+" sent message")
                        .setStyle(new NotificationCompat.BigTextStyle()
                                .bigText(msg))
                        .setContentText(msg);

        mBuilder.setContentIntent(contentIntent);
        mNotificationManager.notify(NOTIFICATION_ID, mBuilder.build());



        //final String mainmsg =msg;
        //final Bitmap mainbitmap =bitmap;
       /* Handler h = new Handler(Looper.getMainLooper());
        h.post(new Runnable(){

            public void run() {
                Toast toast = Toast.makeText(context,
                        ""+mainmsg, Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.CENTER, 0, 0);
                LinearLayout toastView = (LinearLayout) toast.getView();
                ImageView imageCodeProject = new ImageView(context);
                imageCodeProject.setMaxWidth(30);
                imageCodeProject.setMaxHeight(30);
                imageCodeProject.setImageBitmap(mainbitmap);//.setImageResource(bitmap);
                toastView.addView(imageCodeProject, 0);
                toast.show();
            }
        });*/

        //set alert dialog


    }// end message push


    //else if chat
    public void groupChat(String msg, String otherguyname, String otherguy_index, String otherguy_pic){

       //final String mainmsg =msg;

        mNotificationManager = (NotificationManager)
                context.getSystemService(Context.NOTIFICATION_SERVICE);

//when click notification open MessgeInside
        myintent = new Intent(context, MainActivity.class);

        myintent.putExtra("other_guy_index", otherguy_index);
        myintent.putExtra("otherguyname", otherguyname);
        contentIntent = PendingIntent.getActivity(context, 0,
                myintent, PendingIntent.FLAG_UPDATE_CURRENT);

        //otherguy_pic

       /* imageloader = ImageLoader.getInstance();
        if(imageloader.isInited()==false) {

            imageloader.init(ImageLoaderConfiguration.createDefault(context));

        }//end if
*/
        bitmap = null; //(Bitmap)imageloader.loadImageSync("http://tanggoal.com/public/uploads/members_pic/"+otherguy_pic);

        //final Bitmap mainbitmap =bitmap;

         mBuilder =
                new NotificationCompat.Builder(context)

                        .setSmallIcon(R.drawable.ic_launcher)
                       // .setLargeIcon(bitmap)
                        .setAutoCancel(true)
                                //set image
                                //set title
                        .setContentTitle(otherguyname + " commented")
                        .setStyle(new NotificationCompat.BigTextStyle()
                                .bigText(msg))
                        .setContentText(msg);

        mBuilder.setContentIntent(contentIntent);
        mNotificationManager.notify(NOTIFICATION_ID, mBuilder.build());



       /* Handler h = new Handler(Looper.getMainLooper());
        h.post(new Runnable(){

            public void run() {

                Toast toast = Toast.makeText(context,
                        ""+mainmsg, Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.CENTER, 0, 0);
                LinearLayout toastView = (LinearLayout) toast.getView();
                ImageView imageCodeProject = new ImageView(context);
                imageCodeProject.setMaxWidth(30);
                imageCodeProject.setMaxHeight(30);
                imageCodeProject.setImageBitmap(mainbitmap);//.setImageResource(bitmap);
                toastView.addView(imageCodeProject, 0);
                toast.show();
            }
        });*/


       // bitmap.recycle();

    }//end groupchat push


public void battle(String msg, String otherguyname, String otherguy_index, String otherguy_pic){


    mNotificationManager = (NotificationManager)
            context.getSystemService(Context.NOTIFICATION_SERVICE);

//when click notification open MessgeInside
    Intent myintent = new Intent(context, MainActivity.class);

    myintent.putExtra("other_guy_index", otherguy_index);
    myintent.putExtra("otherguyname", otherguyname);
    PendingIntent contentIntent = PendingIntent.getActivity(context, 0,
            myintent, PendingIntent.FLAG_UPDATE_CURRENT);

    //otherguy_pic

    imageloader = ImageLoader.getInstance();
    if(imageloader.isInited()==false) {

        imageloader.init(ImageLoaderConfiguration.createDefault(context));

    }//end if

    bitmap = (Bitmap)imageloader.loadImageSync("http://tanggoal.com/public/uploads/members_pic/"+otherguy_pic);

    mBuilder = new NotificationCompat.Builder(context)

                    .setSmallIcon(R.drawable.ic_launcher)
                    .setLargeIcon(bitmap)
                    .setAutoCancel(true)
                            //set image
                            //set title
                    .setContentTitle(otherguyname + " in Battle:")
                    .setStyle(new NotificationCompat.BigTextStyle()
                            .bigText(msg))
                    .setContentText(msg);

    mBuilder.setContentIntent(contentIntent);
    mNotificationManager.notify(NOTIFICATION_ID, mBuilder.build());


}//end groupchat push


}
