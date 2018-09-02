package kr.soen.connectup4;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.provider.ContactsContract;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by ettur on 2017-11-23.
 */

public class ItemAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<ItemParent> mList;
    private SharedPreferences studentPref;
    private SharedPreferences studentInfoPref;
    private Context context;



    public ItemAdapter(List<ItemParent> list) {
        this.mList = list;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;

        switch (viewType) {
            case ItemParent.ITEM_TITLE:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_title, parent, false);
                return new TitleViewHolder(view);
            case ItemParent.ITEM_AD:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_ad, parent, false);
                return new AdViewHolder(view);
            case ItemParent.ITEM_ENTRY:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_entry, parent, false);
                return new EntryViewHolder(view);
            case ItemParent.ITEM_SEARCHKEYWORD:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_searchkeyword, parent, false);
                return new SearchKeywordViewHolder(view);
            case ItemParent.ITEM_CLASSIFIED:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_classified, parent, false);
                return new ClassifiedViewHolder(view);
            case ItemParent.ITEM_EVENT:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_event, parent, false);
                return new EventViewHolder(view);
            case ItemParent.ITEM_CLASSIFIED_MINI:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_classified_mini, parent, false);
                return new ClassifiedMiniViewHolder(view);
            case ItemParent.ITEM_EVENT_MINI:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_event_mini, parent, false);
                return new EventMiniViewHolder(view);
            case ItemParent.ITEM_BULLETIN:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_bulletin, parent, false);
                return new BulletinViewHolder(view);
            case ItemParent.ITEM_BULLETIN_LIST:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_bulletin_list, parent, false);
                return new BulletinListViewHolder(view);
        }
        return null;

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ItemParent object = mList.get(position);
        if (object != null) {
            switch (object.getType()) {
                case ItemParent.ITEM_TITLE:
                    ItemTitle titleItem = (ItemTitle)object;
                    ((TitleViewHolder) holder).mTitle.setText(titleItem.getTitle());
                    break;
                case ItemParent.ITEM_AD:
                    ItemAd adItem = (ItemAd)object;
                    ((AdViewHolder) holder).mAd.setImageResource(adItem.getImgID());
                    break;
                case ItemParent.ITEM_ENTRY:
                    final ItemEntry entryItem = (ItemEntry)object;
                    ((EntryViewHolder) holder).mTitle.setText(entryItem.getTitle());
                    ((EntryViewHolder) holder).mContent.setText(entryItem.getContent());
                    ((EntryViewHolder) holder).mRel.setOnClickListener(new View.OnClickListener(){
                        @Override
                        public void onClick(View view){
                            Intent intent;
                            Context context = view.getContext();
                            intent = new Intent(context, StudentBulletinListPage.class);
                            intent.putExtra("index", entryItem.getIndex());
                            context.startActivity(intent);
                        }
                    });
                    break;
                case ItemParent.ITEM_SEARCHKEYWORD:
                    ItemSearchKeyword searchKeywordItem = (ItemSearchKeyword)object;
                    ((SearchKeywordViewHolder) holder).mBtn1.setText(searchKeywordItem.getBtn1());
                    ((SearchKeywordViewHolder) holder).mBtn2.setText(searchKeywordItem.getBtn2());
                    ((SearchKeywordViewHolder) holder).mBtn3.setText(searchKeywordItem.getBtn3());
                    break;
                case ItemParent.ITEM_CLASSIFIED:
                    final ItemClassified classifiedItem = (ItemClassified)object;
                    String salaryExperience = classifiedItem.getSalary() + " / " + classifiedItem.getExperience();
                    ((ClassifiedViewHolder) holder).mImage.setImageResource(classifiedItem.getImgID());
                    ((ClassifiedViewHolder) holder).mName.setText(classifiedItem.getName());
                    ((ClassifiedViewHolder) holder).mIntroduction.setText(classifiedItem.getIntroduction());
                    ((ClassifiedViewHolder) holder).mJob.setText(classifiedItem.getJob());
                    ((ClassifiedViewHolder) holder).mSalaryExperience.setText(salaryExperience);
                    if((position % 2) == 1){
                        ((ClassifiedViewHolder) holder).mRel.setBackgroundColor(Color.rgb(243, 251, 255));
                    }
                    ((ClassifiedViewHolder) holder).mRel.setOnClickListener(new View.OnClickListener(){
                        @Override
                        public void onClick(View view){
                            Intent intent;
                            Context context = view.getContext();

                            intent = new Intent(context, StudentClassifiedPage.class);
                            intent.putExtra("index", classifiedItem.getIndex());
                            context.startActivity(intent);
                        }
                    });
                    break;
                case ItemParent.ITEM_EVENT:
                    final ItemEvent eventItem = (ItemEvent)object;
                    context = ((EventViewHolder) holder).mImage.getContext();
                    //Drawable d = ContextCompat.getDrawable(context, eventItem.getImgID());
                    ((EventViewHolder) holder).mImage.setImageResource(R.drawable.ic_event2);
                    ((EventViewHolder) holder).mImage.setColorFilter(context.getColor(R.color.blue));
                    ((EventViewHolder) holder).mName.setText(eventItem.getName());
                    ((EventViewHolder) holder).mDate.setText(eventItem.getDate());
                    ((EventViewHolder) holder).mLocation.setText(eventItem.getLocation());
                    if((position % 2) == 1){
                        ((EventViewHolder) holder).mRel.setBackgroundColor(Color.rgb(243, 251, 255));
                    }
                    ((EventViewHolder) holder).mRel.setOnClickListener(new View.OnClickListener(){
                        @Override
                        public void onClick(View view) {
                            Intent intent;
                            Context context = view.getContext();

                            intent = new Intent(context, StudentEventPage.class);
                            intent.putExtra("index", eventItem.getIndex());
                            context.startActivity(intent);
                        }
                    });
                    break;
                case ItemParent.ITEM_CLASSIFIED_MINI:
                    final ItemClassifiedMini classifiedMiniItem = (ItemClassifiedMini)object;
                    ((ClassifiedMiniViewHolder) holder).mImg1.setImageResource(classifiedMiniItem.getImg1());
                    ((ClassifiedMiniViewHolder) holder).mImg2.setImageResource(classifiedMiniItem.getImg2());
                    ((ClassifiedMiniViewHolder) holder).mImg3.setImageResource(classifiedMiniItem.getImg3());
                    ((ClassifiedMiniViewHolder) holder).mImg4.setImageResource(classifiedMiniItem.getImg4());
                    ((ClassifiedMiniViewHolder) holder).mImg5.setImageResource(classifiedMiniItem.getImg5());

                    ((ClassifiedMiniViewHolder) holder).mText1.setText(classifiedMiniItem.getText1());
                    ((ClassifiedMiniViewHolder) holder).mText2.setText(classifiedMiniItem.getText2());
                    ((ClassifiedMiniViewHolder) holder).mText3.setText(classifiedMiniItem.getText3());
                    ((ClassifiedMiniViewHolder) holder).mText4.setText(classifiedMiniItem.getText4());
                    ((ClassifiedMiniViewHolder) holder).mText5.setText(classifiedMiniItem.getText5());

                    ((ClassifiedMiniViewHolder) holder).mSub1.setText(classifiedMiniItem.getSubtext1());
                    ((ClassifiedMiniViewHolder) holder).mSub2.setText(classifiedMiniItem.getSubtext2());
                    ((ClassifiedMiniViewHolder) holder).mSub3.setText(classifiedMiniItem.getSubtext3());
                    ((ClassifiedMiniViewHolder) holder).mSub4.setText(classifiedMiniItem.getSubtext4());
                    ((ClassifiedMiniViewHolder) holder).mSub5.setText(classifiedMiniItem.getSubtext5());

                    ((ClassifiedMiniViewHolder) holder).mRel1.setOnClickListener(new View.OnClickListener(){
                        @Override
                        public void onClick(View view) {
                            Intent intent;
                            Context context = view.getContext();

                            intent = new Intent(context, StudentClassifiedPage.class);
                            intent.putExtra("index", classifiedMiniItem.getInd1());
                            context.startActivity(intent);
                        }
                    });
                    ((ClassifiedMiniViewHolder) holder).mRel2.setOnClickListener(new View.OnClickListener(){
                        @Override
                        public void onClick(View view) {
                            Intent intent;
                            Context context = view.getContext();

                            intent = new Intent(context, StudentClassifiedPage.class);
                            intent.putExtra("index", classifiedMiniItem.getInd2());
                            context.startActivity(intent);
                        }
                    });
                    ((ClassifiedMiniViewHolder) holder).mRel3.setOnClickListener(new View.OnClickListener(){
                        @Override
                        public void onClick(View view) {
                            Intent intent;
                            Context context = view.getContext();

                            intent = new Intent(context, StudentClassifiedPage.class);
                            intent.putExtra("index", classifiedMiniItem.getInd3());
                            context.startActivity(intent);
                        }
                    });
                    ((ClassifiedMiniViewHolder) holder).mRel4.setOnClickListener(new View.OnClickListener(){
                        @Override
                        public void onClick(View view) {
                            Intent intent;
                            Context context = view.getContext();

                            intent = new Intent(context, StudentClassifiedPage.class);
                            intent.putExtra("index", classifiedMiniItem.getInd4());
                            context.startActivity(intent);
                        }
                    });
                    ((ClassifiedMiniViewHolder) holder).mRel5.setOnClickListener(new View.OnClickListener(){
                        @Override
                        public void onClick(View view) {
                            Intent intent;
                            Context context = view.getContext();

                            intent = new Intent(context, StudentClassifiedPage.class);
                            intent.putExtra("index", classifiedMiniItem.getInd5());
                            context.startActivity(intent);
                        }
                    });
                    break;
                case ItemParent.ITEM_EVENT_MINI:
                    final ItemEventMini eventMiniItem = (ItemEventMini)object;
                    ((EventMiniViewHolder) holder).mImg1.setImageResource(eventMiniItem.getImg1());
                    ((EventMiniViewHolder) holder).mImg2.setImageResource(eventMiniItem.getImg2());
                    ((EventMiniViewHolder) holder).mImg3.setImageResource(eventMiniItem.getImg3());
                    ((EventMiniViewHolder) holder).mImg4.setImageResource(eventMiniItem.getImg4());
                    ((EventMiniViewHolder) holder).mImg5.setImageResource(eventMiniItem.getImg5());

                    ((EventMiniViewHolder) holder).mText1.setText(eventMiniItem.getText1());
                    ((EventMiniViewHolder) holder).mText2.setText(eventMiniItem.getText2());
                    ((EventMiniViewHolder) holder).mText3.setText(eventMiniItem.getText3());
                    ((EventMiniViewHolder) holder).mText4.setText(eventMiniItem.getText4());
                    ((EventMiniViewHolder) holder).mText5.setText(eventMiniItem.getText5());

                    ((EventMiniViewHolder) holder).mSub1.setText(eventMiniItem.getSubtext1());
                    ((EventMiniViewHolder) holder).mSub2.setText(eventMiniItem.getSubtext2());
                    ((EventMiniViewHolder) holder).mSub3.setText(eventMiniItem.getSubtext3());
                    ((EventMiniViewHolder) holder).mSub4.setText(eventMiniItem.getSubtext4());
                    ((EventMiniViewHolder) holder).mSub5.setText(eventMiniItem.getSubtext5());

                    ((EventMiniViewHolder) holder).mRel1.setOnClickListener(new View.OnClickListener(){
                        @Override
                        public void onClick(View view) {
                            Intent intent;
                            Context context = view.getContext();

                            intent = new Intent(context, StudentEventPage.class);
                            intent.putExtra("index", eventMiniItem.getInd1());
                            context.startActivity(intent);
                        }
                    });
                    ((EventMiniViewHolder) holder).mRel2.setOnClickListener(new View.OnClickListener(){
                        @Override
                        public void onClick(View view) {
                            Intent intent;
                            Context context = view.getContext();

                            intent = new Intent(context, StudentEventPage.class);
                            intent.putExtra("index", eventMiniItem.getInd2());
                            context.startActivity(intent);
                        }
                    });
                    ((EventMiniViewHolder) holder).mRel3.setOnClickListener(new View.OnClickListener(){
                        @Override
                        public void onClick(View view) {
                            Intent intent;
                            Context context = view.getContext();

                            intent = new Intent(context, StudentEventPage.class);
                            intent.putExtra("index", eventMiniItem.getInd3());
                            context.startActivity(intent);
                        }
                    });
                    ((EventMiniViewHolder) holder).mRel5.setOnClickListener(new View.OnClickListener(){
                        @Override
                        public void onClick(View view) {
                            Intent intent;
                            Context context = view.getContext();

                            intent = new Intent(context, StudentEventPage.class);
                            intent.putExtra("index", eventMiniItem.getInd5());
                            context.startActivity(intent);
                        }
                    });
                    ((EventMiniViewHolder) holder).mRel4.setOnClickListener(new View.OnClickListener(){
                        @Override
                        public void onClick(View view) {
                            Intent intent;
                            Context context = view.getContext();

                            intent = new Intent(context, StudentEventPage.class);
                            intent.putExtra("index", eventMiniItem.getInd4());
                            context.startActivity(intent);
                        }
                    });
                    break;
                case ItemParent.ITEM_BULLETIN:
                    final ItemBulletin bulletinItem = (ItemBulletin)object;
                    context = ((BulletinViewHolder) holder).mText.getContext();
                    studentPref = context.getSharedPreferences("studentPref", MODE_PRIVATE);
                    studentInfoPref = context.getSharedPreferences("studentInfo", MODE_PRIVATE);
                    String id = studentPref.getString("currentId", "");

                    ((BulletinViewHolder) holder).mText.setText(bulletinItem.getText());

                    if(bulletinItem.getIndex() == 6){
                        ((BulletinViewHolder) holder).mText.setText(studentInfoPref.getString(id+"_uni", "")
                                + " " + bulletinItem.getText());
                    }

                    ((BulletinViewHolder) holder).mRel.setOnClickListener(new View.OnClickListener(){
                        @Override
                        public void onClick(View view){
                            Intent intent;
                            Context context = view.getContext();
                            intent = new Intent(context, StudentBulletinListPage.class);
                            intent.putExtra("index", bulletinItem.getIndex());
                            context.startActivity(intent);
                        }
                    });
                    break;
                case ItemParent.ITEM_BULLETIN_LIST:
                    final ItemBulletinList bulletinListItem = (ItemBulletinList)object;
                    ((BulletinListViewHolder) holder).mTitle.setText(bulletinListItem.getTitle());
                    if(bulletinListItem.getName() != null){
                        ((BulletinListViewHolder) holder).mName.setText(bulletinListItem.getName());
                    }
                    ((BulletinListViewHolder) holder).mRel.setOnClickListener(new View.OnClickListener(){
                        @Override
                        public void onClick(View view){
                            if(bulletinListItem.getIndex() != -1) {
                                Intent intent;
                                Context context = view.getContext();
                                intent = new Intent(context, StudentBulletinPage.class);
                                intent.putExtra("index", bulletinListItem.getIndex());
                                context.startActivity(intent);
                            }
                        }
                    });
                    break;
            }
        }
    }

    @Override
    public int getItemCount() {
        if (mList == null)
            return 0;
        return mList.size();
    }


    @Override
    public int getItemViewType(int position) {
        if (mList != null) {
            ItemParent object = mList.get(position);
            if (object != null) {
                return object.getType();
            }
        }
        return 0;
    }

    public static class TitleViewHolder extends RecyclerView.ViewHolder {
        private TextView mTitle;
        public TitleViewHolder(View itemView) {
            super(itemView);
            mTitle = (TextView) itemView.findViewById(R.id.item_title_title);
        }
    }

    public static class AdViewHolder extends RecyclerView.ViewHolder{
        private ImageView mAd;
        public AdViewHolder(View itemView){
            super(itemView);
            mAd = (ImageView) itemView.findViewById(R.id.item_ad_image);
        }
    }

    public static class EntryViewHolder extends RecyclerView.ViewHolder {
        private TextView mTitle, mContent;
        private RelativeLayout mRel;
        public EntryViewHolder(View itemView) {
            super(itemView);
            mTitle = (TextView) itemView.findViewById(R.id.item_entry_title);
            mContent = (TextView) itemView.findViewById(R.id.item_entry_content);
            mRel = (RelativeLayout) itemView.findViewById(R.id.item_entry_layout);
        }
    }

    public static class SearchKeywordViewHolder extends RecyclerView.ViewHolder {
        private Button mBtn1, mBtn2, mBtn3;
        public SearchKeywordViewHolder(View itemView) {
            super(itemView);
            mBtn1 = (Button) itemView.findViewById(R.id.item_searchkeyword_keyword1);
            mBtn2 = (Button) itemView.findViewById(R.id.item_searchkeyword_keyword2);
            mBtn3 = (Button) itemView.findViewById(R.id.item_searchkeyword_keyword3);
        }
    }

    public static class ClassifiedViewHolder extends RecyclerView.ViewHolder{
        private ImageView mImage;
        private TextView mName, mIntroduction, mJob, mSalaryExperience;
        private RelativeLayout mRel;
        public ClassifiedViewHolder(View itemView){
            super(itemView);
            mImage = (ImageView) itemView.findViewById(R.id.item_classified_image);
            mName = (TextView) itemView.findViewById(R.id.item_classified_name);
            mIntroduction = (TextView) itemView.findViewById(R.id.item_classified_introduction);
            mJob = (TextView) itemView.findViewById(R.id.item_classified_job);
            mSalaryExperience = (TextView) itemView.findViewById(R.id.item_classified_salary_experience);
            mRel = (RelativeLayout) itemView.findViewById(R.id.item_classified_layout);
        }
    }

    public static class EventViewHolder extends RecyclerView.ViewHolder{
        private ImageView mImage;
        private TextView mName, mDate, mLocation;
        private RelativeLayout mRel;
        public EventViewHolder(View itemView){
            super(itemView);
            mImage = (ImageView) itemView.findViewById(R.id.item_event_image);
            mName = (TextView) itemView.findViewById(R.id.item_event_name);
            mDate = (TextView) itemView.findViewById(R.id.item_event_date);
            mLocation = (TextView) itemView.findViewById(R.id.item_event_location);
            mRel = (RelativeLayout) itemView.findViewById(R.id.item_event_layout);
        }
    }

    public static class ClassifiedMiniViewHolder extends RecyclerView.ViewHolder{
        private ImageView mImg1, mImg2, mImg3, mImg4, mImg5;
        private TextView mText1, mText2, mText3, mText4, mText5;
        private TextView mSub1, mSub2, mSub3, mSub4, mSub5;
        private RelativeLayout mRel1, mRel2, mRel3, mRel4, mRel5;
        public ClassifiedMiniViewHolder(View itemView){
            super(itemView);
            mImg1 = (ImageView)itemView.findViewById(R.id.item_classified_mini_img1);
            mImg2 = (ImageView)itemView.findViewById(R.id.item_classified_mini_img2);
            mImg3 = (ImageView)itemView.findViewById(R.id.item_classified_mini_img3);
            mImg4 = (ImageView)itemView.findViewById(R.id.item_classified_mini_img4);
            mImg5 = (ImageView)itemView.findViewById(R.id.item_classified_mini_img5);

            mText1 = (TextView)itemView.findViewById(R.id.item_classified_mini_text1);
            mText2 = (TextView)itemView.findViewById(R.id.item_classified_mini_text2);
            mText3 = (TextView)itemView.findViewById(R.id.item_classified_mini_text3);
            mText4 = (TextView)itemView.findViewById(R.id.item_classified_mini_text4);
            mText5 = (TextView)itemView.findViewById(R.id.item_classified_mini_text5);

            mSub1 = (TextView)itemView.findViewById(R.id.item_classified_mini_subtext1);
            mSub2 = (TextView)itemView.findViewById(R.id.item_classified_mini_subtext2);
            mSub3 = (TextView)itemView.findViewById(R.id.item_classified_mini_subtext3);
            mSub4 = (TextView)itemView.findViewById(R.id.item_classified_mini_subtext4);
            mSub5 = (TextView)itemView.findViewById(R.id.item_classified_mini_subtext5);

            mRel1 = (RelativeLayout)itemView.findViewById(R.id.item_classified_mini_box1);
            mRel2 = (RelativeLayout)itemView.findViewById(R.id.item_classified_mini_box2);
            mRel3 = (RelativeLayout)itemView.findViewById(R.id.item_classified_mini_box3);
            mRel4 = (RelativeLayout)itemView.findViewById(R.id.item_classified_mini_box4);
            mRel5 = (RelativeLayout)itemView.findViewById(R.id.item_classified_mini_box5);
        }
    }

    public static class EventMiniViewHolder extends RecyclerView.ViewHolder{
        private ImageView mImg1, mImg2, mImg3, mImg4, mImg5;
        private TextView mText1, mText2, mText3, mText4, mText5;
        private TextView mSub1, mSub2, mSub3, mSub4, mSub5;
        private RelativeLayout mRel1, mRel2, mRel3, mRel4, mRel5;
        public EventMiniViewHolder(View itemView){
            super(itemView);
            mImg1 = (ImageView)itemView.findViewById(R.id.item_event_mini_img1);
            mImg2 = (ImageView)itemView.findViewById(R.id.item_event_mini_img2);
            mImg3 = (ImageView)itemView.findViewById(R.id.item_event_mini_img3);
            mImg4 = (ImageView)itemView.findViewById(R.id.item_event_mini_img4);
            mImg5 = (ImageView)itemView.findViewById(R.id.item_event_mini_img5);

            mText1 = (TextView)itemView.findViewById(R.id.item_event_mini_text1);
            mText2 = (TextView)itemView.findViewById(R.id.item_event_mini_text2);
            mText3 = (TextView)itemView.findViewById(R.id.item_event_mini_text3);
            mText4 = (TextView)itemView.findViewById(R.id.item_event_mini_text4);
            mText5 = (TextView)itemView.findViewById(R.id.item_event_mini_text5);

            mSub1 = (TextView)itemView.findViewById(R.id.item_event_mini_subtext1);
            mSub2 = (TextView)itemView.findViewById(R.id.item_event_mini_subtext2);
            mSub3 = (TextView)itemView.findViewById(R.id.item_event_mini_subtext3);
            mSub4 = (TextView)itemView.findViewById(R.id.item_event_mini_subtext4);
            mSub5 = (TextView)itemView.findViewById(R.id.item_event_mini_subtext5);

            mRel1 = (RelativeLayout)itemView.findViewById(R.id.item_event_mini_box1);
            mRel2 = (RelativeLayout)itemView.findViewById(R.id.item_event_mini_box2);
            mRel3 = (RelativeLayout)itemView.findViewById(R.id.item_event_mini_box3);
            mRel4 = (RelativeLayout)itemView.findViewById(R.id.item_event_mini_box4);
            mRel5 = (RelativeLayout)itemView.findViewById(R.id.item_event_mini_box5);
        }
    }

    public static class BulletinViewHolder extends RecyclerView.ViewHolder {
        private TextView mText;
        private RelativeLayout mRel;
        public BulletinViewHolder(View itemView) {
            super(itemView);
            mText = (TextView) itemView.findViewById(R.id.item_bulletin_text);
            mRel = (RelativeLayout) itemView.findViewById(R.id.item_bulletin_layout);
        }
    }

    public static class BulletinListViewHolder extends RecyclerView.ViewHolder {
        private TextView mTitle, mName;
        private RelativeLayout mRel;
        public BulletinListViewHolder(View itemView) {
            super(itemView);
            mTitle = (TextView) itemView.findViewById(R.id.item_bulletin_list_title);
            mName = (TextView) itemView.findViewById(R.id.item_bulletin_list_name);
            mRel = (RelativeLayout) itemView.findViewById(R.id.item_bulletin_list_layout);
        }
    }
}
