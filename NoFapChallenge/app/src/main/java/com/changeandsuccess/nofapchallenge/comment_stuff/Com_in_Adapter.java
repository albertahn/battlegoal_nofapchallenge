package com.changeandsuccess.nofapchallenge.comment_stuff;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.changeandsuccess.nofapchallenge.CoachProfile;
import com.changeandsuccess.nofapchallenge.LoginActivity;
import com.changeandsuccess.nofapchallenge.R;
import com.changeandsuccess.nofapchallenge.model.CommentItem;
import com.changeandsuccess.nofapchallenge.model.LoginItem;
import com.changeandsuccess.nofapchallenge.utils.UserDatabase;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;

import java.util.ArrayList;

/**
 * Created by Albert AN on 8/25/14.
 * All Rights Reserved
 *
 *  If you are not the intended recipient, any disclosure, copying, distribution or any action taken or omitted to be taken in reliance on it, is prohibited and may be unlawful.
 *  If you have received this e-mail by mistake, please e-mail the sender by replying to this message, and deleting the original and any printout thereof.

 이 코드의 정보는 대외비이며, 법적으로 보호될 수 있습니다.
 코드는 오직 받는 사람만을 위한 것이고,
 다른 사람의 접근은 허가되지 않습니다.
 의도된 수신자가 아니라면, 발표, 복사,
 배포 등의 행위는 금지되어 있으며, 불법입니다.
 이 코드를 실수로 받은 것이라면, 송신자에게 반송해고, 원본/ 출력물을 삭제해 주십시오.​
 */
public class Com_in_Adapter  extends ArrayAdapter<CommentItem> {
    View rowView;
    ImageView profile_photo;
    ArrayList<LoginItem> generatedLoginItem;
    ImageButton replyButton;
    Button moreCommentsBtn;

    String userIndex;
    static int rowList = R.layout.com_in_adapter_____comment_bubble_list_row;

    private final Activity activity;
    private final Context context;
    private final ArrayList<CommentItem> itemsArrayList;
    private  ArrayList<CommentItem> itemsArrayList_reply;

    ImageLoader imageLoader = ImageLoader.getInstance();
    //imageLoader.destroy();

    String newNumLikes;

    public Com_in_Adapter(Activity context, ArrayList<CommentItem> itemsArrayList,ArrayList<CommentItem> itemsArrayList_reply, String userID) {

        super(context, rowList, itemsArrayList);
        this.activity = context;

        this.context = context;
        this.itemsArrayList = itemsArrayList;
        this.itemsArrayList_reply = itemsArrayList_reply;
        this.userIndex = userID;


        LoginActivity loginActivity = new LoginActivity();

        if(loginActivity.isLoggedIn(activity)){

            UserDatabase info = new UserDatabase(context);
            info.open();
            String[][] data = info.getData();
            info.close();
            userIndex = data[0][1];

        }else{
            //replyButton.setClickable(false);
            //likeButton.setClickable(false);
            userIndex= "1";
        }

        //imageLoader

       if(!imageLoader.isInited()) {

           imageLoader.init(ImageLoaderConfiguration.createDefault(activity));
       }

    }//end interface


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

       final int thisposition = position;

        // 1. Create inflater
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

//check if i sent the message

        final Integer commentindex = Integer.parseInt(itemsArrayList.get(position).getcomment_index().toString());

       int rep_to= itemsArrayList.get(position).getreply_to();

        // 2. Get rowView from inflater
        rowView = inflater.inflate(rowList, parent, false);

        moreCommentsBtn = (Button)rowView.findViewById(R.id.moreCommentsBtn);
        moreCommentsBtn.setVisibility(View.GONE);
        if(rep_to!=0){ //if its a reply change color

            //rowView.setBackgroundResource(R.drawable.gradient_bg);
            //set top

            final int reply_index = (int) itemsArrayList.get(position).getreply_to();

        }else{
        }

            // 3. Get the two text view from the rowView
            TextView text_body_sample = (TextView) rowView.findViewById(R.id.text_body);
            TextView username = (TextView) rowView.findViewById(R.id.user_name);
            TextView replynum = (TextView) rowView.findViewById(R.id.num_reply);
        TextView timestamp = (TextView) rowView.findViewById(R.id.time_stamp);

           final TextView likesnum = (TextView) rowView.findViewById(R.id.num_likes);

            // 4. Set the text for textView
            text_body_sample.setText(itemsArrayList.get(position).getcomment_text());
            username.setText(itemsArrayList.get(position).getusername());
            replynum.setText(""+itemsArrayList.get(position).getreply_num());
            likesnum.setText(""+itemsArrayList.get(position).getLikes());
            timestamp.setText(itemsArrayList.get(position).getTimeStamp());


        if(itemsArrayList.get(position).getreply_num().compareTo("0")==0){
        }else{
            moreCommentsBtn.setVisibility(View.VISIBLE);
            moreCommentsBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    new LoadReplies(userIndex, activity, commentindex).execute();
                }
            });
        }

            profile_photo = (ImageView) rowView.findViewById(R.id.user_profile);

            String imageurl = "http://tanggoal.com/public/uploads/members_pic/" + itemsArrayList.get(position).getprofile_picture();

            DisplayImageOptions options = new DisplayImageOptions.Builder()
                    .showImageForEmptyUri(R.drawable.ic_launcher)
                    .displayer(new RoundedBitmapDisplayer(50))
                    .cacheInMemory(true)
                    .considerExifParams(true)
                    .build();


            if(! itemsArrayList.get(position).getprofile_picture().isEmpty()){

                imageLoader.displayImage(imageurl, profile_photo, options);
                //Log.e("image_for_com",""+itemsArrayList.get(position).getprofile_picture());

                profile_photo.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        String cock = itemsArrayList.get(thisposition).getmembers_index();


                        Intent i = new Intent(activity,
                                CoachProfile.class);

                        i.putExtra("coachID", ""+cock);
                        // i.putExtra("coachName", ""+)
                        activity.startActivity(i);
                    }
                });
            }

            // 5. retrn rowView
           //final Integer commentindex = Integer.parseInt(itemsArrayList.get(position).getcomment_index().toString());
            rowView.setId(commentindex);

        for(int i=0;i<itemsArrayList_reply.size();i++){
            int reply_to = itemsArrayList_reply.get(i).getreply_to();
            if(reply_to==commentindex){
                ListView listview = (ListView) rowView.findViewById(R.id.listView2);
                ReplyAdapterOutside adapter = new ReplyAdapterOutside(activity);
                adapter.addItem(new ReplyItem(itemsArrayList_reply.get(i).getmembers_index(),itemsArrayList_reply.get(i).getusername(),itemsArrayList_reply.get(i).getcomment_text(),itemsArrayList_reply.get(i).getprofile_picture()));
                listview.setAdapter(adapter);
            }
        }

        //get the rep buttons


         replyButton = (ImageButton) rowView.findViewById(R.id.reply_button);
        final ImageButton likeButton = (ImageButton) rowView.findViewById(R.id.like_button);
        //check login

       /* LoginHelper loginHelper = new LoginHelper();

        String[][] loginData = loginHelper.checkLogin(activity);

        generatedLoginItem = generateData(loginData);


        if(generatedLoginItem.toString()=="[]") {
            replyButton.setVisibility(View.GONE);
            likeButton.setVisibility(View.GONE);
        }
*/

//button for replies


            replyButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    /*UserDatabase info = new UserDatabase(context);
                    info.open();
                    String[][] data = info.getData();
                    info.close();

                   String userIndex = data[0][1];*/

                  new  LoadReplies(userIndex, activity, commentindex).execute();

                }
            });


        likeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               /* UserDatabase info = new UserDatabase(context);
                info.open();
                String[][] data = info.getData();
                info.close();

                String userIndex = data[0][1];*/

                LikeButton likebtn = new LikeButton(userIndex, activity);

                try {
                    String newlikes = likebtn.execute(commentindex + "").get();
                    likesnum.setText(""+newlikes);

                    likeButton.setBackgroundResource(R.drawable.btn_blue);

                    likeButton.setEnabled(false);

                }catch (Exception e){


                }

               //String newlikes = likebtn.getNewNumLikes();


            }
        });

        //set the delete if user

        final String comment_person_index = itemsArrayList.get(position).getmembers_index();

       // Log.e("comment_person_index",""+comment_person_index);


//get deleteComment
        ImageButton deleteComment = (ImageButton) rowView.findViewById(R.id.delete_comment_btn);

        if(comment_person_index.equals(userIndex)){ //if userindex
            deleteComment.setVisibility(View.VISIBLE);

            deleteComment.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                   /* Dialog d = new Dialog(activity);
                    d.setTitle(commentindex+"delete comment"+":"+comment_person_index);
                    d.show();*/

                    setDeleteDialog(commentindex);

                }
            });

        }else{
            deleteComment.setVisibility(View.GONE);


        }


            //reply_rel.setId(position);

            return rowView;


        }//end else


    public static ArrayList<LoginItem> generateData(String[][] data){
        ArrayList<LoginItem> items = new ArrayList<LoginItem>();

        for (int i =0; i<data.length ; i++){

            items.add(new LoginItem( data[i][1], data[i][2], data[i][3],data[i][4],data[i][5],data[i][6],data[i][7]));

        }
        return items;
    } //end generate


    //delete comment


    public void setDeleteDialog(int commentID){

       final int commentIndex = commentID;


        final View rootView = ((Activity) activity).getWindow().getDecorView().findViewById(android.R.id.content);

        AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);

        // Setting Dialog Title
        alertDialog.setTitle("Confirm Delete...");

        // Setting Dialog Message
        alertDialog.setMessage("Are you sure you want delete this?");

        // Setting Positive "Yes" Button
        alertDialog.setPositiveButton("YES", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog,int which) {

                ProgressBar progressBar = (ProgressBar) rootView.findViewById(R.id.input_progress_bar);
                progressBar.setVisibility(View.VISIBLE);

                new DeleteComment(activity, ""+commentIndex, userIndex).execute();

                // Write your code here to invoke YES event
                Toast.makeText(context, "Deleted"+commentIndex, Toast.LENGTH_SHORT).show();
            }
        });

        // Setting Negative "NO" Button
        alertDialog.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                // Write your code here to invoke NO event
                Toast.makeText(context, "not deleted", Toast.LENGTH_SHORT).show();
                dialog.cancel();
            }
        });

        // Showing Alert Message
        alertDialog.show();
    }//end delete

}