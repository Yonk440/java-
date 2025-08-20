package ui;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

public class GameJFrame extends JFrame implements KeyListener, ActionListener {

    //定义一个二维数组,用来管理数据
    int[][] data = new int[4][4];
    //定义两个变量，记录空白的位置
    int x;
    int y;
    //定义一个变量来记录当前图片的路径
    String path = "image/animal/animal3/";
    //定义一个正确的二维数组
    int[][] win = new int[][]{
            {1, 2, 3, 4},
            {5, 6, 7, 8},
            {9, 10, 11, 12},
            {13, 14, 15, 0}
    };
    //定义一个变量来记录步数
    int step = 0;

    //创建选项下面的条目对象
    JMenuItem replayItem = new JMenuItem("重新游戏");
    JMenuItem reLoginItem = new JMenuItem("重新登录");
    JMenuItem closeItem = new JMenuItem("关闭游戏");
    JMenuItem accountItem = new JMenuItem("公众号");
    //二级菜单的条目对象
    JMenuItem girlItem = new JMenuItem("美女");
    JMenuItem animalItem = new JMenuItem("动物");
    JMenuItem sportItem = new JMenuItem("运动");

    //创建一个游戏的主界面
    public GameJFrame() {

        //初始化界面
        initJFrame();

        //初始化菜单
        initJMenuBar();

        //初始化数据
        initData();

        //初始化游戏的图片
        initImage();

        //设置界面可见,要放在最后
        this.setVisible(true);
    }

    private void initData() {
        //把一个0-15的一维数组打乱顺序，再按照4个一组的方式添加到二维数组中去
        int[] arr = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15};
        Random rand = new Random();
        for (int i = arr.length - 1; i > 0; i--) {
            int index = rand.nextInt(i + 1);
            int temp = arr[i];
            arr[i] = arr[index];
            arr[index] = temp;
        }
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                data[i][j] = arr[i * 4 + j];
                if(data[i][j] == 0){
                    x = i;
                    y = j;
                }
            }
        }
    }
    private void initImage() {
        //注意：先加载的图片会在上方，后加载的图片会在下方
        //清空原本存在的图片
        this.getContentPane().removeAll();

        //判断是否胜利
        if(isWin()){
            //显示胜利的图标
            ImageIcon winIcon = new ImageIcon("image/win.png");
            JLabel winjlabel = new JLabel(winIcon);
            winjlabel.setBounds(203,283,197,73);
            this.getContentPane().add(winjlabel);
        }

        //添加步数的文字标签
        JLabel stepJLabel = new JLabel("步数：" + step);
        stepJLabel.setBounds(50,30,100,20);
        this.getContentPane().add(stepJLabel);

        //添加游戏图片
        for(int i = 0; i<4; i++){
            for(int j = 0; j<4; j++){
                //获取当前图片的序号
                int number = data[i][j];
                //1.创建图片ImageIcon的对象
                ImageIcon icon = new ImageIcon(path + number + ".jpg");
                //2.创建一个JLabel对象（管理容器）
                JLabel jLabel = new JLabel(icon);
                //3.设置图片位置
                jLabel.setBounds(105 * j + 83,105 * i + 134 ,105,105);
                //给每张图片添加边框，可以填0或1
                jLabel.setBorder(new BevelBorder(BevelBorder.LOWERED));
                //4.把管理容器添加到界面中
                this.getContentPane().add(jLabel);
            }
        }

        //添加背景图片
        ImageIcon bg = new ImageIcon("image/background.png");
        JLabel background = new JLabel(bg);
        background.setBounds(40,40,508,560);
        this.getContentPane().add(background);

        //刷新界面
        this.getContentPane().repaint();
    }
    private void initJMenuBar() {
        //创建菜单对象
        JMenuBar jMenuBar = new JMenuBar();
        //创建两个选项的对象（功能，关于我们）
        JMenu functionJMenu = new JMenu("功能");
        JMenu aboutJMenu = new JMenu("关于我们");
        //创建一个选项下的二级菜单
        JMenu changeMenu = new JMenu("更换图片");

        //将选项下的条目添加到选项中
        functionJMenu.add(changeMenu);//二级菜单
        functionJMenu.add(replayItem);
        functionJMenu.add(reLoginItem);
        functionJMenu.add(closeItem);
        aboutJMenu.add(accountItem);

        changeMenu.add(girlItem);
        changeMenu.add(animalItem);
        changeMenu.add(sportItem);

        //给选项下的条目绑定事件
        replayItem.addActionListener(this);
        reLoginItem.addActionListener(this);
        closeItem.addActionListener(this);
        accountItem.addActionListener(this);
        girlItem.addActionListener(this);
        animalItem.addActionListener(this);
        sportItem.addActionListener(this);

        //将菜单里的两个选项添加到菜单中
        jMenuBar.add(functionJMenu);
        jMenuBar.add(aboutJMenu);

        //给整个界面设置菜单
        this.setJMenuBar(jMenuBar);

        //将菜单添加到界面中
        this.setJMenuBar(jMenuBar);
    }
    private void initJFrame() {
        //设置界面的大小
        this.setSize(603,680);
        //设置界面的标题
        this.setTitle("拼图游戏 v1.0");
        //设置界面居中
        this.setLocationRelativeTo(null);
        //设置界面置顶
        this.setAlwaysOnTop(true);
        //设置关闭模式
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        //取消默认的图片居中位置
        this.setLayout(null);
        //给界面添加键盘监听事件
        this.addKeyListener(this);
    }
    private void springFrame(){
        //创建一个弹框对象
        JDialog jd = new JDialog();
        //添加一个图片
        ImageIcon ab = new ImageIcon("image/about.png");
        JLabel abjlabel = new JLabel(ab);
        abjlabel.setBounds(0,0,258,258);
        //把容器添加到对象中去
        jd.getContentPane().add(abjlabel);
        //给弹框设置大小
        jd.setSize(344,344);
        //设置弹框置顶
        jd.setAlwaysOnTop(true);
        //设置弹框居中
        jd.setLocationRelativeTo(null);
        //设置弹框不关闭则不能操作其他界面
        jd.setModal(true);
        //显示弹框
        jd.setVisible(true);
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }
    @Override
    public void keyPressed(KeyEvent e) {
        //在最开始之前先判断游戏是否胜利，若胜利则此方法直接结束
        if(isWin()){
            return;//返回结果或者结束方法
        }


        //如果按住A健不松，则显示完整图片
        if(e.getKeyCode() == 65){
            //首先清除图片
            this.getContentPane().removeAll();
            //再加载完整图片和背景图片
            ImageIcon ap = new ImageIcon(path + "all.jpg");
            JLabel allp = new JLabel(ap);
            allp.setBounds(83,134,420,420);
            this.getContentPane().add(allp);

            ImageIcon bg = new ImageIcon("image/background.png");
            JLabel background = new JLabel(bg);
            background.setBounds(40,40,508,560);
            this.getContentPane().add(background);
            //刷新界面
            this.getContentPane().repaint();
        }
    }
    @Override
    public void keyReleased(KeyEvent e) {
        //在最开始之前先判断游戏是否胜利，若胜利则此方法直接结束
        if(isWin()){
            return;//返回结果或者结束方法
        }

        //对上下左右进行判断
        //37 38 39 40分别对应左上右下
        int keyCode = e.getKeyCode();
        System.out.println("按下的键是：" + keyCode);
        if(keyCode == 37){
            System.out.println("向左移动");
            //判断是否可以移动
            if(y == 3){//空白在最右边。无法向左移动
                return;
            }
            //交换数据
            int temp = data[x][y];
            data[x][y] = data[x][y + 1];
            data[x][y + 1] = temp;
            //改变空白的位置
            y++;
            //步数增加
            step++;
            //刷新界面
            initImage();
        }else if(keyCode == 38){
            System.out.println("向上移动");
            //判断是否可以移动
            if(x == 3){//空白在最下面。无法向上移动
                return;
            }
            //交换数据
            int temp = data[x][y];
            data[x][y] = data[x + 1][y];
            data[x + 1][y] = temp;
            //改变空白的位置
            x++;
            //步数增加
            step++;
            //刷新界面
            initImage();
        }else if(keyCode == 39){
            System.out.println("向右移动");
            //判断是否可以移动
            if(y == 0){//空白在最左边。无法向右移动
                return;
            }
            //交换数据
            int temp = data[x][y];
            data[x][y] = data[x][y - 1];
            data[x][y - 1] = temp;
            //改变空白的位置
            y--;
            //步数增加
            step++;
            //刷新界面
            initImage();
        }else if(keyCode == 40){
            System.out.println("向下移动");
            //判断是否可以移动
            if(x == 0){//空白在最上面。无法向下移动
                return;
            }
            //交换数据
            int temp = data[x][y];
            data[x][y] = data[x - 1][y];
            data[x - 1][y] = temp;
            //改变空白的位置
            x--;
            //步数增加
            step++;
            //刷新界面
            initImage();
        }else if(keyCode == 65) {//松开了A健
            initImage();
        }else if(keyCode == 87){//按下了W健则直接胜利
            data = new int[][]{
                    {1, 2, 3, 4},
                    {5, 6, 7, 8},
                    {9, 10, 11, 12},
                    {13, 14, 15, 0}
            };
            //刷新界面
            initImage();
        }
    }

    //判断是否胜利
    private boolean isWin(){
        for(int i = 0; i<4; i++){
            for(int j = 0; j<4; j++){
                if(data[i][j] != win[i][j]){
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //获取被点击的条目对象
        Object obj = e.getSource();
        if(obj == girlItem){
            System.out.println("更换图片为美女");
            Random rand = new Random();
            int num = rand.nextInt(13) + 1;
            path = "image/girl/girl"+num+"/";
            //重新打乱数据
            initData();
            //步数清零
            step = 0;
            //刷新界面
            initImage();
        }
        if(obj == animalItem){
            System.out.println("更换图片为动物");
            Random rand = new Random();
            int num = rand.nextInt(8) + 1;
            path = "image/animal/animal"+num+"/";
            //重新打乱数据
            initData();
            //步数清零
            step = 0;
            //刷新界面
            initImage();
        }
        if(obj == sportItem){
            System.out.println("更换图片为运动");
            Random rand = new Random();
            int num = rand.nextInt(10) + 1;
            path = "image/sport/sport"+num+"/";
            //重新打乱数据
            initData();
            //步数清零
            step = 0;
            //刷新界面
            initImage();
        }
        if(obj == replayItem){
            System.out.println("重新游戏");
            //重新打乱数据
            initData();
            //步数清零
            step = 0;
            //刷新界面
            initImage();
        }else if(obj == reLoginItem){
            System.out.println("重新登录");
            //关闭当前的游戏界面
            this.dispose();
            //打开登录界面
            new LoginJFrame();
        }else if(obj == closeItem){
            System.out.println("关闭游戏");
            //直接关闭虚拟机
            System.exit(0);
        }else if(obj == accountItem){
            System.out.println("公众号");
            springFrame();
        }
    }
}
