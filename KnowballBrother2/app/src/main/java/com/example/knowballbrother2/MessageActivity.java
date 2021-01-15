package com.example.knowballbrother2;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class MessageActivity extends AppCompatActivity {

    private List<Message> messageList =new ArrayList<>();

    private TextView tv_message_title;
    private TextView tv_list_1,tv_list_2,tv_list_3;
    private TextView tv_football,tv_basketball,tv_volleyball,tv_pingpong,tv_badminton;
    private ImageView iv_football,iv_basketball,iv_volleyball,iv_pingpong,iv_badminton;
    private ImageView iv_message_picture;
    private ListView list_message;
    private Button bt_message,bt_user;
    private String user;
    private DBHelper dbHelper;

    private void init(){
        tv_message_title = (TextView)findViewById(R.id.tv_message_title);
        iv_message_picture = (ImageView)findViewById(R.id.iv_message_picture);
        tv_list_1 = findViewById(R.id.tv_list_1);
        tv_list_2 = findViewById(R.id.tv_list_2);
        tv_list_3 = findViewById(R.id.tv_list_3);
        tv_football = findViewById(R.id.tv_football);
        tv_basketball = findViewById(R.id.tv_basketball);
        tv_volleyball = findViewById(R.id.tv_volleyball);
        tv_pingpong = findViewById(R.id.tv_pingpong);
        tv_badminton = findViewById(R.id.tv_badminton);
        iv_football = findViewById(R.id.iv_football);
        iv_basketball = findViewById(R.id.iv_basketball);
        iv_volleyball = findViewById(R.id.iv_volleyball);
        iv_pingpong = findViewById(R.id.iv_pingpong);
        iv_badminton = findViewById(R.id.iv_badminton);
        list_message = findViewById(R.id.list_message);
        bt_message = findViewById(R.id.bt_message);
        bt_user = findViewById(R.id.bt_user);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);
        init();

        Bundle bundle = getIntent().getExtras();
        user = bundle.getString("user");

        dbHelper = new DBHelper(this,"KnowBall.db",null,3);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor cursor = db.query("Message",null,null,null,null,null,null);
        if(cursor.moveToFirst()){
            do{
                String kind = cursor.getString(cursor.getColumnIndex("message_kind"));
                if(kind.equals("football")){
                    String title = cursor.getString(cursor.getColumnIndex("message_title"));
                    String content = cursor.getString(cursor.getColumnIndex("message_content"));
                    Message message = new Message(R.drawable.picture,title,content);
                    messageList.add(message);
                }
            }while(cursor.moveToNext());
        }
        cursor.close();

        //initMessage_football();
        final MessageAdapter adapter = new MessageAdapter(MessageActivity.this,R.layout.message_item,messageList);
        final ListView list_message = (ListView)findViewById(R.id.list_message);
        list_message.setAdapter(adapter);

        list_message.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ListView listView = (ListView)parent;
                Message message = (Message) listView.getItemAtPosition(position);
                Integer picture = message.getPicture();
                String title = message.getTitle();
                String content = message.getContent();

                Intent intent = new Intent(MessageActivity.this,EvaluateActivity.class);
                Bundle bundle = new Bundle();
                bundle.putInt("picture",picture);
                bundle.putString("title",title);
                bundle.putString("content",content);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

        tv_list_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ranktitle = tv_list_1.getText().toString();
                Intent intent =new Intent(MessageActivity.this,RankActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("ranktitle",ranktitle);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

        bt_user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MessageActivity.this,UserActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("user",user);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

        tv_football.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iv_football.setAlpha((float)1);
                iv_basketball.setAlpha((float)0);
                iv_volleyball.setAlpha((float)0);
                iv_pingpong.setAlpha((float)0);
                iv_badminton.setAlpha((float)0);
                tv_list_1.setText("进 攻 榜");
                tv_list_2.setText("积 分 榜");
                tv_list_3.setText("助 攻 榜");
                adapter.clear();
                adapter.notifyDataSetChanged();;
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                Cursor cursor = db.query("Message",null,null,null,null,null,null);
                if(cursor.moveToFirst()){
                    do{
                        String kind = cursor.getString(cursor.getColumnIndex("message_kind"));
                        if(kind.equals("football")){
                            String title = cursor.getString(cursor.getColumnIndex("message_title"));
                            String content = cursor.getString(cursor.getColumnIndex("message_content"));
                            Message message = new Message(R.drawable.picture,title,content);
                            messageList.add(message);
                        }
                    }while(cursor.moveToNext());
                }
                cursor.close();
                MessageAdapter adapter = new MessageAdapter(MessageActivity.this,R.layout.message_item,messageList);
                final ListView list_message = (ListView)findViewById(R.id.list_message);
                list_message.setAdapter(adapter);
            }
        });

        tv_basketball.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iv_football.setAlpha((float)0);
                iv_basketball.setAlpha((float)1);
                iv_volleyball.setAlpha((float)0);
                iv_pingpong.setAlpha((float)0);
                iv_badminton.setAlpha((float)0);
                tv_list_1.setText("得 分 榜");
                tv_list_2.setText("助 攻 榜");
                tv_list_3.setText("时 长 榜");
                adapter.clear();
                adapter.notifyDataSetChanged();
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                Cursor cursor = db.query("Message",null,null,null,null,null,null);
                if(cursor.moveToFirst()){
                    do{
                        String kind = cursor.getString(cursor.getColumnIndex("message_kind"));
                        if(kind.equals("basketball")){
                            String title = cursor.getString(cursor.getColumnIndex("message_title"));
                            String content = cursor.getString(cursor.getColumnIndex("message_content"));
                            Message message = new Message(R.drawable.picture,title,content);
                            messageList.add(message);
                        }
                    }while(cursor.moveToNext());
                }
                cursor.close();
                MessageAdapter adapter = new MessageAdapter(MessageActivity.this,R.layout.message_item,messageList);
                final ListView list_message = (ListView)findViewById(R.id.list_message);
                list_message.setAdapter(adapter);
            }
        });

        tv_volleyball.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iv_football.setAlpha((float)0);
                iv_basketball.setAlpha((float)0);
                iv_volleyball.setAlpha((float)1);
                iv_pingpong.setAlpha((float)0);
                iv_badminton.setAlpha((float)0);
                tv_list_1.setText("得 分 榜");
                tv_list_2.setText("助 攻 榜");
                tv_list_3.setText("积 分 榜");
                adapter.clear();
                adapter.notifyDataSetChanged();
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                Cursor cursor = db.query("Message",null,null,null,null,null,null);
                if(cursor.moveToFirst()){
                    do{
                        String kind = cursor.getString(cursor.getColumnIndex("message_kind"));
                        if(kind.equals("volleyball")){
                            String title = cursor.getString(cursor.getColumnIndex("message_title"));
                            String content = cursor.getString(cursor.getColumnIndex("message_content"));
                            Message message = new Message(R.drawable.picture,title,content);
                            messageList.add(message);
                        }
                    }while(cursor.moveToNext());
                }
                cursor.close();
                MessageAdapter adapter = new MessageAdapter(MessageActivity.this,R.layout.message_item,messageList);
                final ListView list_message = (ListView)findViewById(R.id.list_message);
                list_message.setAdapter(adapter);
            }
        });

        tv_pingpong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iv_football.setAlpha((float)0);
                iv_basketball.setAlpha((float)0);
                iv_volleyball.setAlpha((float)0);
                iv_pingpong.setAlpha((float)1);
                iv_badminton.setAlpha((float)0);
                tv_list_1.setText("胜 场 榜");
                tv_list_2.setText("胜 率 榜");
                tv_list_3.setText("时 长 榜");
                adapter.clear();
                adapter.notifyDataSetChanged();
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                Cursor cursor = db.query("Message",null,null,null,null,null,null);
                if(cursor.moveToFirst()){
                    do{
                        String kind = cursor.getString(cursor.getColumnIndex("message_kind"));
                        if(kind.equals("pingpong")){
                            String title = cursor.getString(cursor.getColumnIndex("message_title"));
                            String content = cursor.getString(cursor.getColumnIndex("message_content"));
                            Message message = new Message(R.drawable.picture,title,content);
                            messageList.add(message);
                        }
                    }while(cursor.moveToNext());
                }
                cursor.close();
                MessageAdapter adapter = new MessageAdapter(MessageActivity.this,R.layout.message_item,messageList);
                final ListView list_message = (ListView)findViewById(R.id.list_message);
                list_message.setAdapter(adapter);
            }
        });

        tv_badminton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iv_football.setAlpha((float)0);
                iv_basketball.setAlpha((float)0);
                iv_volleyball.setAlpha((float)0);
                iv_pingpong.setAlpha((float)0);
                iv_badminton.setAlpha((float)1);
                tv_list_1.setText("胜 场 榜");
                tv_list_2.setText("胜 率 榜");
                tv_list_3.setText("时 长 榜");
                adapter.clear();
                adapter.notifyDataSetChanged();
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                Cursor cursor = db.query("Message",null,null,null,null,null,null);
                if(cursor.moveToFirst()){
                    do{
                        String kind = cursor.getString(cursor.getColumnIndex("message_kind"));
                        if(kind.equals("badminton")){
                            String title = cursor.getString(cursor.getColumnIndex("message_title"));
                            String content = cursor.getString(cursor.getColumnIndex("message_content"));
                            Message message = new Message(R.drawable.picture,title,content);
                            messageList.add(message);
                        }
                    }while(cursor.moveToNext());
                }
                cursor.close();
                MessageAdapter adapter = new MessageAdapter(MessageActivity.this,R.layout.message_item,messageList);
                final ListView list_message = (ListView)findViewById(R.id.list_message);
                list_message.setAdapter(adapter);
            }
        });


    }

    private void initMessage_football(){
        Message m1 =new Message(R.drawable.football_1,"热血驰骋球场，浙商大足球少年用热爱书写青春","听！绿茵场上的呐喊逐渐清晰\n" +
                "镜头拉近\n" +
                "一群带着足球飞奔的少年\n" +
                "他们身形矫健\n" +
                "速度快到照片定格的那一刻只有虚影\n" +
                "他们是浙商大的校足球队！\n" +
                "寒风凛凛 吹灭不了他们训练的热情\n" +
                "小雨蒙蒙 阻挡不了他们前行的脚步\n" +
                "他们披荆斩棘 不畏艰难险阻\n" +
                "他们鲜衣怒马 赢得一身荣光\n" +
                "他们的主要成就\n" +
                "2016年浙江省青少年校园足球联赛\n" +
                "（大学男子a组）第三名\n" +
                "2018年浙江省青少年校园足球联赛\n" +
                "（大学男子b组）第五名\n" +
                "2019年浙江省大学生运动会足球赛事 16强\n" +
                "2020年浙江省青少年校园足球联赛\n" +
                "（大学男子b组）第二名\n" +
                "校队从17年从a组降级到现在已经三年，队员也换了一届又一届，但是大家心里都憋着一口气，想着有朝一日一定要升级回去。\n" +
                "面对2020年的佳绩,队员丁予来回忆道:\n" +
                "“今年我们的阵容完成大换血，打法也更加直接、高效，热身赛打的也非常好，所以在出发去比赛时,我预感今年我们一定会升级。虽然小组赛签运不好，但是凭着我们的努力和一些客观因素，提前一轮就确定了小组头名出线，全队上下都非常专注和自信。”\n" +
                "到了淘汰赛，第一场就进入了残酷的点球大战，前三轮足球队全部罚失，被逼入绝境。\n" +
                "但是他们信念不灭，最终让三追四完成了惊天逆转，挺进半决赛。半决赛也是有惊无险的拿下对手，顺利升级，完成了三年的夙愿。\n" +
                "虽然最终只拿到亚军，但这整个系列赛带给球队的财富是无穷的。队员的气质、心态、执行力都得到了很大的成长。\n" +
                "丁予来微笑道:“这不仅仅是球场上的收获，在球场下我们也因为这次比赛变得更加的自信、成熟，兄弟们也变得更加的团结，我觉得这是超越比赛本身的更加珍贵的收获。”");
        messageList.add(m1);
        Message m2 =new Message(R.drawable.football_2,"女生杯决赛|会计学院一举夺金","2020年12月9日15:30分，女生杯足球决赛打响，由会计对阵信息。\n" +
                "哨响，比赛开始。双方球员你来我往，不相上下。\n" +
                "23分钟，足球飞到信息8号管可怡面前，8号急于解球不慎摔倒，触球犯规。\n" +
                "临近半场结束，信息获得一次中场任意球机会，10号马佳琪精准吊射，但对方门将反应迅速，将球稳稳抱住。\n" +
                "第36分钟，信息队手球，会计18号王红雪球门左前方吊射，球越过人墙，可惜高过门框。\n" +
                "第44分钟，一阵混战后，足球传到会计30号刘思懿面前，30号球员一记绝妙吊射越过人群，以刁钻角度射入。\n" +
                "第58分钟，比赛最后关头，信息10号马佳琪获得任意球机会，世界波香蕉球直挂死角，绝平比赛。比分1:1.\n" +
                "比赛进入点球大战阶段。双方球员你来我往，最后会计在点球阶段以2：0战胜信息。\n" +
                "比赛结束。最终，会计3:1战胜信息，夺得了去年错失的冠军，取得了今年女生杯足球赛的金杯！信息虽然错失蝉联冠军的机会，但队员们在比赛中的优秀表现依然可圈可点。感谢这两支球队的辛苦付出和精彩表现！");
        messageList.add(m2);
    }

    private void initMessage_basketball(){
        Message m1 =new Message(R.drawable.basketball_1,"全国总冠军！浙商大女篮勇夺中国大学生篮球二级联赛金牌","刚刚，浙江工商大学女子篮球队在第二十二届CUBA中国大学生篮球赛二级联赛全国总决赛冠亚军决赛中，以65比60战胜桂林电子科技大学队，成为全国总冠军。\n" +
                "哨响高高跃起跳球，浙江工商大学13号队员马佳琪杀入内线，拿下队内首球，极大的鼓舞了士气；6号队员叶雨梦一个变向将球以飞快的速度传了出去，篮球在空中划出了一道美丽的弧线，再次传到马佳琪的手中，站定，屈膝，三分球，进！“漂亮！”看着球进了，她们相视一笑。\n" +
                "比赛开始一分钟时，队内的“篮板王”14号队员林子涵因为受伤只能回到位置休息，少了一位中流砥柱的队员，浙江工商大学起初暂时落后，最大差距一度被拉到7分；下半场时，林子涵又重新走回赛场，在她的配合下，叶雨梦强势连投，马佳琪罚球追平比分，浙商大调整策略，拉大分差……\n" +
                "学校老师介绍，这支冠军球队里并没有专业的体育生，而是队长叶雨梦在大二时和教练员商量后着手组建的，成立不过两年，十分年轻。\n" +
                "叶雨梦今年大三，被评为“本届二级联赛个人实力最突出的球员”，在此前的比赛中包揽了“得分王”、“助攻王”和“抢断王”，成为队伍的绝对核心。\n" +
                "在这次比赛中，她也选择打满全场，一次次完美的接连进球总在最后时刻屡屡追上分差，帮助球队紧咬比分，逆转局势；最终以34分、7个篮板的成绩拿下全场MVP。\n" +
                "浙商大甲组女篮运队员共12人，高强度训练为她们一路走来打好基础。而“粉丝们”的支持则是她们在场上不放弃的底气。受疫情影响，比赛一律采用空场的形式，但队员们收到的鼓励却一点没少。\n" +
                "在“CUBA浙江工商大学后援团”微信群里，校友们热情高涨，边收看直播，边第一时间为她们“刷屏打call”，作为二级联赛决赛中唯一的一支浙江队伍，来自浙江其他高校的不少粉丝们也纷纷为她们助力。\n" +
                "校女篮队第一次参加全国比赛就冲到冠军，浙江工商大学副校长赵英军在现场说，这群女篮队员都是学校校训精神最好的实践者，也是学校近几年文体振兴计划推行取得的成效之一，希望学校其他队伍以她们为榜样，拼出自己的新水平新高度。");
        messageList.add(m1);
        Message m2 =new Message(R.drawable.basketball_2,"「传承杯篮球赛」管工男篮三连胜，强势进入半决赛！","秋爽阴天，热血更盛，我们对篮球的热情肆意昂扬。\n" +
                "2020年10月28日下午4点“传承杯“淘汰赛，管工学院对阵工商管理学院。\n" +
                "我院17号队员沈江涛的一记二分球拉开了本场比赛的帷幕，队员们默契的团队合作、精准的运球投篮使比赛异常精彩激烈、扣人心弦。\n" +
                "第一节比赛我院以9：3暂时领先工商管理学院\n" +
                "第二节比赛开始，球员们个个激情高昂，一轮轮的抢球、防守、上篮，动作潇洒、帅气利索，我院越战越勇，以19：9的比分拿下上半场比赛。\n" +
                "第三节比赛中，工商管理学院球员调整战术后开始发力，我院球员因体力下降，出现几次失误，对手将比分追至19：11，比赛进入白热化阶段。我院球员迅速调整状态，准确地抢断、犀利地突破，13号队员樊耀天一个巧妙的转身投进一个3分球，最终我院以30：18的比分遥遥领先对手赢得第三节比赛。\n" +
                "第四节比赛中，23号队员孙杰防守攻击不断变换频频得分，其他球员团结一致、全神贯注、乘胜追击，将比分差距持续拉大。意犹未尽中一声哨响，我院以45：25的绝对优势赢得全场比赛，进入”传承杯“半决赛！\n" +
                "在本场比赛中我院全体球员发挥更高、更快、更强的奥林匹克精神，奋力争先，充分展示了大学生进取向上的精神风貌和素质水平。赛场上，他们挥洒汗水，尽情享受比赛，赢得了观众们的阵阵掌声。希望在接下来的半决赛中，能够继续发挥实力赛出水平，给我们呈现更加精彩的体育竞技风采。");
        messageList.add(m2);
    }

    private void initMessage_volleyball(){
        Message m1 =new Message(R.drawable.volleyball_1,"“新生杯”排球赛","赛程安排\n" +
                "女排\n" +
                "10.10（星期六）\n" +
                "12:30-13:30  东语VS工商  旅游VS法学\n" +
                "16:30-17:30  经济VS艺设  泰隆VS人文\n" +
                "10.12（星期一）\n" +
                "12:30-13:30  财会VS金融  统计VS信息\n" +
                "16:30-17:30  泰隆VS经济  艺设VS环境\n" +
                "10.14（星期三）\n" +
                "12:30-13:30  东语VS财会  法学VS统计\n" +
                "16:30-17:30  管工VS公管   人文VS环境\n" +
                "10.17（星期六）\n" +
                "9：00-10：00  东语VS金融  法学VS信息\n" +
                "10：00-11：00  公管VS信电  人文VS艺设\n" +
                "14：00-15：00  环境VS经济  旅游VS统计\n" +
                "15：00-16：00  外语VS管工  工商VS财会\n" +
                "10.19（星期一）\n" +
                "12:30-13:30  工商VS金融  旅游VS信息\n" +
                "16:30-17:30  外语VS信电  泰隆VS艺设\n" +
                "10.21（星期三）\n" +
                "12:30-13:30  人文VS经济 公管VS外语\n" +
                "16:30-17:30 信电VS管工  泰隆VS环境\n" +
                "10.24（星期六）\n" +
                "12:30-13:30  A1-C2 A2-C1\n" +
                "16:30-17:30  B1-D2 B2-D1\n" +
                "10.26（星期一）\n" +
                "12:30-13:30  A-C\n" +
                "16:30-17:30  B-D\n" +
                "10.28 (星期三)\n" +
                "12:30-13:30  三四名决赛\n" +
                "16:30-17:30  冠亚军决赛\n" +
                "男排\n" +
                "10.10（星期六）\n" +
                "12:30-13:30  旅游VS经济\n" +
                "16:30-17:30  金融VS信电\n" +
                "10.12（星期一）\n" +
                "12:30-13:30  法学VS工商\n" +
                "16:30-17:30  管工VS信息\n" +
                "10.14（星期三）\n" +
                "12:30-13:30  旅游VS环境\n" +
                "16:30-17:30  金融VS人文\n" +
                "10.17（星期六）\n" +
                "9：00-10：00  法学VS统计\n" +
                "10：00-11：00  管工VS公共管理\n" +
                "11：00-12：00  经济VS环境\n" +
                "14：00-15：00信电VS人文\n" +
                "10.19（星期一）\n" +
                "12:30-13:30  工商管理VS统计\n" +
                "16:30-17:30  信息VS公共管理\n" +
                "10.21（星期三）\n" +
                "12:30-13:30  A1-C2 A2-CI\n" +
                "16:30-17:30  B1-D2 B2-D1\n" +
                "10.24（星期六）\n" +
                "12:30-13:30  A-C\n" +
                "16:30-17:30  B-D\n" +
                "10.26 (星期一)\n" +
                "12:30-13:30  三四名决赛\n" +
                "16:30-17:30  冠亚军决赛\n" +
                "赛事预告\n" +
                "10.10（星期六）\n" +
                "12:30-13:30\n" +
                "女排：东语VS工商  旅游VS法学\n" +
                "男排：旅游VS经济\n" +
                "16:30-17:30\n" +
                "女排：经济VS艺设  泰隆VS人文\n" +
                "男排：金融VS信电");
        messageList.add(m1);
        Message m2 =new Message(R.drawable.volleyball_2,"“新生杯”排球赛拍了拍你","“新生杯”排球赛\n" +
                "我们不见不散\n" +
                "经过了愉快的八天小长假\n" +
                "我们即将迎来一年一度的“新生杯”排球赛\n" +
                "是不是很期待自己学院队伍的表现呢\n" +
                "既然如此\n" +
                "那就带上你的小伙伴们一起\n" +
                "相约排球场\n" +
                "来一睹运动员们的风采吧\n" +
                "也可以为他（她）们加油助威哦\n" +
                "“新生杯”排球赛一般于每年的10月举行，分为男子组比赛和女子组比赛，由当年入学的新生代表各自学院参加，旨在挖掘人才、培养新秀，为下半年的“商大杯”做准备。同时，“新生杯”的举办也能够让同学们更多的参与到排球这项令每一个中国人骄傲的体育运动中来，让同学们更好地了解排球喜欢排球。在丰富课娱、加强锻炼的同时，促进同学之间的交流，加强同学之间的合作精神，营造更好的排球文化氛围。\n" +
                "我们抓住了假期的尾巴，在8日下午四点举行了庄重愉悦的抽签仪式，现在就来看一看结果如何吧：\n" +
                "女排分组\n" +
                "A组：东语学院  工商管理学院\n" +
                "财会学院  金融学院\n" +
                "B组：公共管理学院  外国语学院\n" +
                "管工学院  信电学院\n" +
                "C组：法学院  旅游学院\n" +
                "统计学院  信息学院\n" +
                "D组：人文学院  泰隆金融学院\n" +
                "环境学院  艺设学院  经济学院\n" +
                "男排分组\n" +
                "A组：旅游学院 经济学院 环境学院\n" +
                "B组：金融学院 信电学院 人文学院\n" +
                "C组：法学院 工商管理学院 统计学院\n" +
                "D组：管工学院 信息学院 公共管理学院\n");
        messageList.add(m2);
    }

    private void initMessage_pingpong(){
        Message m1 =new Message(R.drawable.pingpong_1,"叮——你有一条新生杯乒乓球赛的消息待查看","嗨 \n" +
                "大家下午好。\n" +
                "一年一度的新生杯乒乓球赛就要与大家见面了。下面是本次比赛的相关信息。\n" +
                "比赛形式\n" +
                "个人赛分为男子单打和女子单打。\n" +
                "要求为2017级大一新生。\n" +
                "比赛采用11球制，三局两胜。\n" +
                "个人赛\n" +
                "团体赛的模式为三男两女，共五场单打。\n" +
                "每个队伍可以且仅可以有一名学长学姐。\n" +
                "5人队伍中需要有一名队长。\n" +
                "个人赛：\n" +
                "1.小组循环（4-6人一组）出前1或前2（按照具体报名人数来分组确定出线人数）\n" +
                "2.出线者打淘汰赛至决出8强\n" +
                "3.比赛11球制，三局两胜，用球为40+新材料球\n" +
                "团体赛：\n" +
                "1.分小组进行小组循环，出线1或2个队伍，具体看报名队伍而定\n" +
                "2.出线小组进行淘汰制比赛，直至决出前八\n" +
                "\n" +
                "3.每个队伍每人一场单打，有三人获胜则本队胜出\n" +
                "4.比赛 11球制，三局两胜，用球为40+新材料球\n" +
                "PS：最好自带球拍哦，若没有球拍届时国球社会准备少量备用拍，可供借用。");
        messageList.add(m1);
        Message m2 =new Message(R.drawable.pingpong_2,"为你说说浙商大“炫影”国球社|欢迎加入！！","浙江工商大学炫影国球社，由原浙江工商大学乒乓球协会更名而成，成立于2003年，目前已有十三年的历史，是校内具有较长历史与较大声誉的社团之一。作为2016年浙江省大学生乒乓球联赛举办地，浙江工商大学体育馆迎来了一批又一批乒乓球高手，也给我校同学带来了更多的锻炼和切磋球技的机会。在来年的大运会等活动中，我校体育馆将作为举办场馆。近水楼台先得月，来炫影国球社，掌握各大高大上比赛第一手内部资讯！！！\n" +
                "社团是一个严肃的认真的正能量的高大上社团！（认真脸.JPG）致力于发展校内外的乒乓球运动，为校内的广大乒乓球爱好者提供一个参赛、交友、切磋与一体的服务平台，集合能人志士为校园乒乓球事业添砖加瓦，推动全球某商大的乒乓球事业走向世界！\n" +
                "仿佛还在昨天，现任社长因为高中学姐的推荐加入了有爱的乒协，而现在那时的关爱我们教导我们的会长已经离开学校、挥手远别。加入乒协后，我最大的感触就是有爱、有趣和极浓的人情味，从会长到同事的干事都是热情洋溢、乐于助人的，与他们一起工作、打球是我大学生活中难忘的回忆。也还记得干事见面会时的节目表演，记得KTV里的纵情歌唱，还有日租房的锅碗瓢盆。我认识，熟悉又送别了两届的会长、干事，虽然无限伤感，但是我仍期待着与你的相知相熟，直到再一次别离。\n" +
                "经历过社团的人都说，参加一个社团是因为无知、好奇，而留在一个社团是因为一份无法割舍的感情，即便曲终人散。旋转的小球拉近了中美关系，相信也可以带你来到我面前，开始一个新的故事。\n" +
                "炫影国球社 我们 等你");
        messageList.add(m2);
    }

    private void initMessage_badminton(){
        Message m1 =new Message(R.drawable.badminton_1,"浙商大羽毛球学院杯","许久未见的太阳明媚而热切\n" +
                "大家所期待的羽毛球学院杯\n" +
                "于3月23日在体育中心开展\n" +
                "我院艺设羽毛球队对战工商、金融 \n" +
                "大家在周六早上准时到达了场地\n" +
                "认真观看了其他学院的热身赛\n" +
                "并积极的进行了上场准备\n" +
                "首先第一轮我院对战工商管理\n" +
                "面对对方选手的强实力\n" +
                "队员们依旧全力以赴投入\n" +
                "第一轮结束 \n" +
                "虽然没有取得第一场的胜利\n" +
                "但队员仍认真准备第二轮与金融的对赛\n" +
                "虽然最后成绩可能并未达到预期\n" +
                "但是大家在比赛前后\n" +
                "所体现出来的的精神依旧令人敬佩\n" +
                "不论是赛前认真约练的态度\n" +
                "比赛中所表现出来的团队凝聚力\n" +
                "全力以赴的体育精神\n" +
                "还是赛后接受比赛结果的坦然\n" +
                "都表现出了大家\n" +
                "对于学院的高度责任感和荣誉感\n" +
                "全力以赴，就不遗憾");
        messageList.add(m1);
        Message m2 =new Message(R.drawable.badminton_2,"在浙商大打羽毛球是一种怎样的感觉？","四月清和雨乍晴，温暖的春天就快过去了，炎日已透出几分夏日的气息，而五月的锦标赛也快到了。羽毛球馆里热火朝天的训练，划过低空的白色弧线，校队队员们悄然加紧了训练的脚步。今天官微君邀请到了校羽毛球队队长赵旭飞和优秀队员余超豪两位帅气的小哥,为浙小商们揭秘校羽毛球队，揭秘球场上的“你来我往”。\n" +
                "We can fly.\n" +
                "我校校队众多，羽毛球队作为其中的佼佼者，不知水平又是如何呢？对于这个问题，赵旭飞破自豪地告诉官微君，如果能正常发挥，省前八也是可以冲一下的。“他很厉害的，一会儿还要坐火车去比赛，”余超豪在一边偷偷泄密道，“队长的水平高超，平时要和他切磋，还要先和我们这群小弟们‘过过招’呢。”赵学长听到这里，似乎有些脸红，不好意思地摸了摸头上“FLY（飞）”字样的帽子。\n" +
                "说到帽子，却有个有趣的缘故，对他们队来说也算有着特殊含义。“骑车到球馆的路上风大，就戴个帽子保持发型，久而久之，每个人都养成了戴帽子的习惯。”无论是去训练，还是一起出征比赛，帽子已经成为他们校羽毛球队独一无二的特色，也成为他们“革命”友谊的象征。群体性强，就是他们能够走过风雨、战胜对手的良方。\n" +
                "因为热爱，所以存在\n" +
                "除了每周二、周四14点到16点统一的训练，队员们还会利用空余时间到球场训练，以保证每周10小时的练习时间。当然，学习方面也不会落下。\n" +
                "两位小哥都在球场上取得过骄人的成绩，但其实他们并没有受到过专业的训练，羽球伴着他们一路成长一路打球过来，除了说是热爱，似乎没有什么别的可以坚持下来的理由了。\n" +
                "“因为喜欢，总会有时间。”他们这样告诉说。有时候不是不行，不是不能而是我们不够爱，不够坚持。只要坚持，终会绽放属于自己的光彩。\n" +
                "使命感在闪闪发光\n" +
                "有很多坚持是因为某种情怀或某个念想，而对于校队的运动员来说，驱使他们不断努力、不断向前的，是一种使命感——不给学校留下点荣誉，总觉得还不能离开。\n" +
                "我们的校羽队是一支尚年轻的队伍，正式成立是在2014年9月。校队的教练周卉老师是国家队的退役运动员，因为有了在国家队的经验，她的指导显得更专业，“可以很明显地感觉到教练的训练更为正规、系统，这能让我们每个人尽最大可能地发挥自己的优点。”而对于五月底的团体锦标赛，他们是跃跃欲试，表示会努力减少个人失误来争取最好成绩。\n" +
                "运动让彼此距离更近\n" +
                "对于很多人来说，言语往往难以准确的表达深切的感情，这时运动就成了一种很好的方式。挥舞球拍的瞬间，共同的节奏、体力的流逝、默契的眼神、惬意的笑容，一点点拉近了彼此的距离。");
        messageList.add(m2);
    }
}
