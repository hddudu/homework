package com.hongdu.gupao.template;

/**
 * @ClassName IntroductionHttp
 * @Description 模板模式的一点思考：
 *      其实是对固定流程化的东西的一个简化，把流程中不变的部分抽象到一个抽象类中，
 *      对可能变化的部分使用钩子方法微调流程的不同点
 *      ，模板模式就是通过抽象类来定义一个逻辑模板，逻辑框架、逻辑原型，然后将无法决定的部分抽象成抽象类交由子类来实现，
 *      一般这些抽象类的调用逻辑还是在抽象类中完成的。这么看来，模板就是定义一个框架，比如盖房子，
 *      我们定义一个模板：房子要封闭，有门，有窗等等，
 *      但是要什么样的门，什么样的窗，这些并不在模板中描述，
 *      这个交给子类来完善，比如门使用防盗门，窗使用北向的窗等等
 * @Author dudu
 * @Date 2019/6/18 22:46
 * @Version 1.0
 */
public class Why {
    public static void main(String[] args) {

        //vip课程:
        //网络课程：
        //创建课程
        //发布预习资料
        //制作ppt课件
        //在线直播
        //提交课件、 提交源码
        //布置作业，有的话就检查作业

        //造房流程：
        //建房子
        //造门
        //造窗
        //造墙
        //大理石建地基
        //是否建厕所 ： 使用钩子方法

        //上班流程：
        //起床
        //穿衣服
        //穿鞋子
        //拿牙刷
        //挤牙膏
        //拿毛巾
        //上厕所
        //洗手
        //刷牙
        //洗脸
        //是否带电脑回来
        // 背电脑： 带电脑回来了就背电脑
        //看时间是否来得及
        //坐班车
        //去公司上班

        //中午吃饭流程：
        //上个厕所，清空肚子
        //准备饭卡
        //是否订外卖
        //走到食堂
        //拿起盘子，准备打饭，打菜
        //找个桌子拿起筷子准备吃饭

        //王者荣耀中的模板模式：
        //英雄：   身体 ： 头 + 手 + 腿
        //       + 皮肤


//        代码中，一次请求开始要进行 ip检查，登录检查，xss注入转义，日志埋点...这就是工厂模式
//        开发业务的同学在业务代码里搞物流，搞装修，搞外卖，搞海淘...这就是策略模式

//        电源适配，usb适配
//        典型项目：openapi；微信登录 php,java,C,.net 对接...

        //简单工厂模式：
        //抽象一个接口，
        //多个类实现接口，
        //需要获取类的实例时，通过接口的方法来创建：（传入相应的参数可以获取相应的类的实例）
        //例如： 角色-》 阿珂，关羽，凯，李白==》抽象一个角色接口，相应的人物实现接口
        //接口的属性： 血量+魔法量+技能+购买价格+角色其他属性
        //简单工厂违背了开闭原则（里面包含了必要的逻辑判断）

        //抽象工厂模式
        //多个类不变，新增一些角色工厂： 如阿珂角色工厂，专门创造阿珂这个实例；
        //抽象出一个抽象工厂类
        //手机和电脑都可以操作： 英雄、防御塔、小兵

        //策略模式 （商店的优惠策略）
        //正常价格 九折英雄 满18888-3000
        //简单工厂重在实例的生成，在客户端里会有工厂类和实例类相对耦合高
        //策略模式客户端只认识context实例，耦合降低

        //代理模式（我将代理小兵惩罚你）
        // 其实有没有发现，你在打游戏的时候，每次打怪升级过程，就是一种代理模式，你（伟大的召唤师）代替小兵打败对方的小兵，获取金币和经验。
        //代理场景就是游戏攻击敌人，里面有攻击、放技能、获取金币。所以抽象类应该这样写：
        //        接下来，代理人(选择的英雄)需要干这些件事
        //代理人：英雄 代替小兵去攻击对面敌人
        //        最后被代理人的实体：
        //小兵是一个被代理人，英雄代理小兵去攻击敌人

        //6.奥义第六式·装饰模式（给你的英雄装配超弦时装）
        //在选择英雄时，游戏给人物角色装配一些武器、上衣、裤子等等，如果更换这些部件该如何优雅替换呢。
        //就是买装备嘛
        //首先先以这些部件进行抽象，展示这些部件，抽象类叫Component,里面有一个展示函数
        //接下来一个比较关键的一步，实现组件后引用自身抽象组件做为成员变量，装饰抽象类，建立依赖关系，可以先穿衣服再穿裤子再配匕首
        ////装饰配件抽象类
        //紧身衣类：
        //匕首类：
        //短裤

        //7.奥义第七式·模板方法模式（我有一套百战百胜铭文）
        //在游戏中，每个人都有一套铭文，每个铭文都固定10个位置，以红色、绿色、蓝色区分。那么游戏提供了这一套模板，怎么搭配就交给召唤师（你)了。
        //首先来个铭文抽象，把红色、绿色、蓝色的10个坑写成函数
        //那么把最重要坑的填充位置留出来，给实现类来填。是不是很熟悉的一个模式。


        //8.奥义第八式·外观模式（游戏开始前和游戏结束后）
        //开始游戏等待界面是激动的，游戏结束弹出胜利是欢喜的，那么这两个时刻游戏系统干了些什么。
        //
        //答案是：开始的时候，游戏要加载角色层、地图层、操作层....等等，游戏结束的时候，就开始销毁角色层、地图层、操作层.....等等，这些是不是期待一个统一管理类
        //首先来个这个抽象，里面就是游戏开始和游戏结束，然后里面装的是所有层
        //人物层：
        //地图层：
        //操作层：
        //这个模式很常用但是说不出他的名字，外观模式也叫门面模式，关系图如下


        //9.奥义第九式·建造者模式（我要选择一个很强的角色）
        // 在游戏里，每次选择角色，系统就会给你生成一个角色，这个角色有漂亮脸蛋的旦妃、有胸肌外露的程咬金、有脚下踩云的姜子牙。在程序中如何实现？
        //先来建造器抽象，造头、造身体、造左胳膊、造右胳膊、造左腿、造右腿
        //在这个模式里，最重要的一个位置出现了，指挥者也就是you（伟大的召唤师），你想选择哪个角色就造哪个角色

        //10.奥义第十式·观察者模式（百里守约我怎么杀不死你）
        //  相信玩个游戏的人，会把百里守约这个英雄禁掉，不让他出场，他有几个特点隐身、3技能位移最关键是他会放一个2技能静谧之眼，可以侦查敌人的视野。一发现敌人过来，立刻逃跑或攻击
        //
        //写这个模式关键两个问题，就是了解两个东西，订阅的主题和通知的对象。主题是敌人来了，通知对象：百里守约或者辅助等等
        //订阅主题抽象：添加一个监听者和删除一个监听者，并通知他干一件事情
        //通知对象抽象类：更新自己的行为
        ///敌人来了主题：
        //通知百里守约和辅助具体实现：
        //

        /**
         * 11.奥义第十一式·状态模式（关羽你的秘密好多啊）
         *
         * 场景：
         *
         *          一个玩的6的关羽，那简直是天下无敌，遇神杀神、见佛杀佛的厉害角色。想成为这个的关羽，首先要了解关羽几种状态。关羽 行走时会增加能量，当能量达到100时，赤兔马处于奔跑状态，随时可以放大招，这时123技能各不相同，1可以向前大刀顶，2可以踩晕敌人，3可以推倒对面一群人。所以关羽的状态这么多，程序如何实现呢？
         *
         * 状态模式可以单独分为两部分来看，一个是谁发出的状态（关羽），另一个是表现出来的状态（抽象）
         *
         * 我们先写谁发出的状态
         */

        //关羽的状态（抽象），注意里引用了关羽的对象，这样子类不断可以传承下去

        //奔跑状态：

        //1技能顶的状态

        //2技能踩状态

        //3技能推倒状态

        //

        /**
         * 12.奥义第十二式·适配器模式（老外想玩游戏看不懂中文啊）
         * 王者荣耀在中国排行榜，遥遥领先，现在大街小巷都在玩，外国朋友也开始玩。可以在中国只有中文版本，选择英雄时或者释放技能时都是中文提示，老外入门看不懂中文，这时候有个翻译就好了。
         *
         * 先抽象一个游戏，里面选择英雄、放技能函数
         *
         */



    }
}
