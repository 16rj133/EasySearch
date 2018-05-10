package com.android.wordsmanagesystem.base;

/**
 * Created by 杨婷 on 2018/2/21.
 */
import java.util.ArrayList;
import android.content.Context;
import com.android.wordsmanagesystem.R;
public class NewsUtils {
    public static ArrayList<NewsBean> getAllNews(Context context) {
        ArrayList<NewsBean> arrayList = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            NewsBean newsBean1 = new NewsBean();
            newsBean1.title = "港交所金融大会堂举行开幕礼暨新春开市仪式";
            newsBean1.des = "国际在线报道（记者 年永刚）：20日，香港交易及结算所新春开市，并举行香港金融大会堂的启用仪式。香港特别行政区行政长官林郑月娥出席并在致辞中表示，港交所迁入金融大会堂是香港金融事业新的一页，未来希望香港专业的金融服务能帮助海外及内地企业发展实体经济。";
            newsBean1.icon = context.getResources().getDrawable(R.drawable.wuhan);
            newsBean1.news_url = "http://www.dzwww.com/xinwen/guojixinwen/201802/t20180220_17060967.htm";
            newsBean1.main = "借贷 p2p 流程 doc";
            arrayList.add(newsBean1);

            NewsBean newsBean2 = new NewsBean();
            newsBean2.title = "中国将对虚拟货币境外交易平台网站实施监管";
            newsBean2.des = "新京报快讯(记者刘景丰)4日傍晚，央行主管媒体《金融时报》官方网站“中国金融新闻网”发文称，中国将对虚拟货币境外交易平台网站采取监管措施，采取包括取缔相关商业存在，取缔、处置境内外虚拟货币交易平台网站等在内的一系列监管措施，以防范金融风险，维护金融稳定。";
            newsBean2.icon = context.getResources().getDrawable(R.drawable.eyu);
            newsBean2.news_url = "http://news.sina.com.cn/c/2018-02-04/doc-ifyreuzn2769789.shtml";
            newsBean2.main = "产品 基金 流程 ppt";
            arrayList.add(newsBean2);

            NewsBean newsBean3 = new NewsBean();
            newsBean3.title = "2017中国TOP金融榜揭晓 立马理财荣登榜单";
            newsBean3.des = "1月10日,“新金融·新发展 2018金融发展高峰论坛暨2017中国TOP金融榜颁奖典礼”在北京举行，本次活动由澎湃新闻主办，由中国人民大学国际货币研究所特别支持。国内知名互联网金融平台立马理财受邀参加此次论坛，与众多业内人士共话经济发展新态势。";
            newsBean3.icon = context.getResources().getDrawable(R.drawable.qihuan);
            newsBean3.news_url = "http://news.ifeng.com/a/20180131/55635421_0.shtml";
            newsBean3.main = "股票 资料 p2p 利率";
            arrayList.add(newsBean3);
        }
        return arrayList;
    }
}
