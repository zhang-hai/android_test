# android_test
## Android项目中的单元测试 ##

> 测试用例采用MVP + dagger架构，网络层采用Retrofit2 + Rxjava，使用登录做了一个简单测试。
> 测试框架使用：Junit、[mockito](https://github.com/mockito/mockito)、[robolectric](https://github.com/robolectric/robolectric)。

## 一、首先看下针对MVP各层单元测试选型 ##
在demo中，MVP各层所使用的单元测试框架如下图所示：
![image](http://upload-images.jianshu.io/upload_images/13549630-1f4b73f54f95dd26.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

- P层：不需要任何Android环境，因此使用Junit测试即可
- V层：使用Robolectric进行UI的测试
- M层：涉及到数据库、网络等相关操作，因此需要依赖Android环境，使用AndroidJUnitRunner进行测试


 > 注：针对网络测试：需要将请求网络的异步请求转化成同步请求，才能获取到结果

### 二、测试框架引入说明 ###
在无特殊需求的情况下可直接按下方式引入：

    testImplementation 'junit:junit:4.12'//Junit测试框架,创建项目自动引入
	//Mockito框架引入
	testImplementation 'org.mockito:mockito-core:2.11.0'
	//robolectric 开源测试ui库
    testImplementation 'org.robolectric:robolectric:3.8'

其中mockito不支持mock匿名类、final类、static方法、private方法,如需支持可以使用PowerMock解决这些问题，如使用PowerMock则需引入以下库：

    //引入PowerMock框架，必须对应mockito的版本，这里2.8.0-2.8.9 对应powermock的1.7.x版本
    testImplementation 'org.mockito:mockito-core:2.8.9'
    testImplementation 'org.powermock:powermock-module-junit4:1.7.3'
    testImplementation 'org.powermock:powermock-api-mockito2:1.7.3'

如果引入的PowerMock使用@Rule规则进行mock则还需要引入另外两个库，这种方式可以有效的防止@RunWith被占用：

	//注意这里是mockito2
    //这里为使用@Rule方式引入要依赖的两个库，@Rule方式引入防止@RunWith被占用
    testImplementation 'org.powermock:powermock-module-junit4-rule:1.7.3'
    testImplementation 'org.powermock:powermock-classloading-xstream:1.7.3'


>注：引入均使用testImplementation方式，所以测试代码要写在test目录下，而非androidTest目录

### 三、几个测试框架的用法简单描述 ###
#### 1.Junit框架 ####
Junit相信很多人都熟悉，测试Java代码基础测试工具。在Android创建项目的时候，默认添加的依赖就有Junit4.x版本的，主要功能就是断言。
###### 常用的断言方法如下： ######

| 方法名        | 方法描述  |
| ------------- |:-------------:|
| assertEquals         | 断言传入的预期值与实际值是相等的          |
| assertNotEquals      | 断言传入的预期值与实际值是不相等的        |
| assertArrayEquals    | 断言传入的预期数组与实际数组是相等的      |
| assertNull           | 断言传入的对象是不为空                  |
| assertNotNull        | 断言传入的对象是不为空                  |
| assertTrue           | 断言条件为真                           |
| assertFalse          | 断言条件为假                           |
| assertSame           | 断言两个对象引用同一个对象，相当于“==”    |
| assertNotSame        | 断言两个对象引用不同的对象，相当于“!=”    |
| assertThat           | 断言实际值是否满足指定的条件             |

>注意：上面的每一个方法，都有对应的重载方法，可以在前面加一个String类型的参数，表示如果断言失败时的提示。

###### 常用注解方法： ######

| 注解名        | 含义  |
| ------------- |:-------------:|
| @Test         | 表示此方法为测试方法                    |
| @Before       | 在每个测试方法前执行，可做初始化操作      |
| @Afte         | 在每个测试方法后执行，可做释放资源操作    |
| @Ignore       | 忽略的测试方法                         |
| @BeforeClass  | 在类中所有方法前运行。此注解修饰的方法必须是static void |
| @AfterClass   | 在类中最后运行。此注解修饰的方法必须是static void      |
| @RunWith      | 指定该测试类使用某个运行器              |
| @Parameters   | 指定测试类的测试数据集合                |
| @Rule         | 重新制定测试类中方法的行为              |
| @FixMethodOrder | 指定测试类中方法的执行顺序            |

>执行顺序：@BeforeClass –> @Before –> @Test –> @After –> @AfterClass

简单例子：

	public class ExampleUnitTest {

	    @Rule
	    public MyRule myRule = new MyRule();
	
	    @Test
	    public void addition_isCorrect() throws Exception {
	        assertEquals(4, 2 + 2);
	    }
	}


#### 2.Mockito框架使用简单说明 ####

在实际的单元测试中，我们测试的类之间会有或多或少的耦合，导致我们无法顺利的进行测试，这时我们就可以使用Mock模拟一个对象，替换我们原先依赖的真实对象，这样我们就可以避免外部的影响，只测试本类，得到更准确的结果。当然它的功能不仅仅只是这些，测试驱动开发也是一大亮点。

Mockito是一个简单的Mock框架，可以帮助我们创建Mock对象，保持单元测试的独立性。

>注：Mock出的对象全都是虚拟的对象，其中非void方法都将返回默认值，比如int方法将返回0，对象方法将返回null等，而void方法将什么都不做。比如：Mock一个List对象，如果获取这个List对象的size大小将返回是0

###### 1.mockito有四种Mock方式，推荐使用下面方式： ######

	@Mock		//<--使用@Mock注解
	public User user;
	@Rule		//<--使用@Rule
	MockitoRule mockitoRule = MockitoJunit.rule(); 

###### 2.常用的Mock打桩方法(设置预期值)如下： ######
>通过设置预期明确Mock对象执行时会发生什么，比如返回特定的值、抛出一个异常、触发一个事件等，又或者调用一定的次数。

| 方法名        | 方法描述  |
| ------------- |:-------------:|
| thenReturn(T value)                    | 设置要返回的值                    |
| thenThrow(Throwable… throwables)       | 设置要抛出的异常      |
| thenAnswer(Answer<?> answer)           | 对结果进行拦截    |
| doReturn(Object toBeReturned)          | 提前设置要返回的值                         |
| doThrow(Throwable… toBeThrown)         | 提前设置要抛出的异常 |
| doAnswer(Answer answer)                | 提前对结果进行拦截      |
| doCallRealMethod()                     | 调用某一个方法的真实实现              |
| doNothing()                            | 设置void方法什么也不做                |

例子

	//使用then打桩方法
    @Test
    public void testUserReturn(){
        //使用then
        //注意 !!! when参数必须调用的是method，如果调用的是公有变量，报错误
        Mockito.when(mUser.getUserName()).thenReturn("小明");
        Mockito.when(mUser.getAge()).thenThrow(new NullPointerException("性别不正确"));

        //输出小明
        System.out.println(mUser.getUserName());

        //抛出异常
	    System.out.println(mUser.getAge());

    }


>特别说明：Mock出的对象只能通过以上方法进行设置预期值，如果直接调用对象中的方法去设值，将是无效的。例如：Mock一个List对象，如果调用add方法添加值是无效的。

###### 3.常用验证方法 ######
有时候我们可能不关心返回结果，而是关心方法有否被正确的参数调用过，这时候就应该使用验证方法了。
verify(T mock)验证发生的某些行为 。

| 方法名        | 方法描述  |
| ------------- |:-------------:|
| after(long millis)                    | 在给定的时间后进行验证                    |
| timeout(long millis)                  | 验证方法执行是否超时      |
| atLeast(int minNumberOfInvocations)   | 至少进行n次验证    |
| atMost(int maxNumberOfInvocations)    | 至多进行n次验证                         |
| description(String description)       | 验证失败时输出的内容 |
| times(int wantedNumberOfInvocations)  | 验证调用方法的次数      |
| never()                               | 验证交互没有发生,相当于times(0)              |
| only()                                | 验证方法只被调用一次，相当于times(1)                |

例子

	//after 在给定的时间后进行验证
    Mockito.verify(mUser,Mockito.after(1000)).getAge();
	//至少验证2次
    Mockito.verify(mUser, Mockito.atLeast(2)).getAge();
###### 4.Spy一个真实对象 ######
Mock操作的全是虚拟对象，比如我们Mock一个List,即使设置了Mockito.when(list.get(0)).thenReturn("hello"),实际上list的size大小扔为0。那么我们如果想操作一个真实的对象怎么办呢？
其实Mockito给我们提供了一个对真实对象操作的办法，就是Spy。下面看下例子，就能明白了

    @Test
    public void testList(){
        List<String> list0 = new ArrayList<>();
        List list = Mockito.spy(list0);
        list.add("1111111");
        list.add("222222222");
        //设置预期值,必须在不越界的情况下设置，否则会越界
        Mockito.when(list.get(0)).thenReturn("hello");
        System.out.println(list.get(0));
        System.out.println(list.get(1));
        System.out.println("list size : " + list.size());
        System.out.println("list0 size : " + list.size());

    }
打印的结果如下：

	hello
	222222222
	list size : 2
	list0 size : 2

###### 5.Mockito还有其他的用法 ######

可以参考：[Mockito框架的使用](https://blog.csdn.net/qq_17766199/article/details/78450007)

如需用到PowerMock参考：[PowerMock使用](https://blog.csdn.net/qq_17766199/article/details/78573390)

#### 3.Robolectric框架使用简单说明 ####
Robolectric通过实现一套JVM能运行的Android代码，从而做到脱离Android运行环境进行测试。
###### 1.使用方法 ######

	@RunWith(RobolectricTestRunner.class)
	@Config(constants = BuildConfig.class)
	public class MainActivityTest {
	
	}

###### 2.日志输出转换 ######
Java中大多使用System.out.println()输出日志，而在Android中多使用Log。但是在单元测试中无法输出Log的信息，这时我们就要使用ShadowLog进行转换。

	@Before
    public void setUp(){
        //输出日志
        ShadowLog.stream = System.out;
    }

这样 Log日志都将输出在控制面板中。

###### 3.robolectric能做的事情 ######

可以验证Activity、ui组件、service、BroadcastReceiver、Dialog、Toast、Fragment、获取Application、访问资源以及验证Activity和Fragment的生命周期等，功能十分强大，主要使用到ShadowXX做验证。
比如之前例子中的ShadowActivity、ShadowLog、ShadowAlertDialog等。Shadow在实现的同时，拓展了原本的Android代码，实现了许多便于测试的功能，比如例子中用到的 getNextStartedActivity、ShadowToast.getTextOfLatestToast()、ShadowAlertDialog.getLatestAlertDialog()。同时也可以自定义自己的Shadow。

具体例子可以参考:demo工程test目录下的robolectric

###### 4.参考资料 ######

[Robolectric使用教程（中文版）](http://blog.hanschen.site/2016/12/10/robolectric.html)

[Robolectric doc](http://robolectric.org/)

### 四、测试代码覆盖率 ###

#### 1.配置 ####

一般情况下，需要在Android studio中做如下配置才能查看到覆盖率

![image](http://upload-images.jianshu.io/upload_images/13549630-68b2cc6321631e77.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

![image](http://upload-images.jianshu.io/upload_images/13549630-e27a81ad56f7c409.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

#### 2.查看覆盖率 ####
做完上面配置，点击一个测试的类，使用Run xx with Coverage，运行完成就会弹出一个覆盖率界面，如下图：
![image](http://upload-images.jianshu.io/upload_images/13549630-c505d34d34dcafa0.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

![image](http://upload-images.jianshu.io/upload_images/13549630-acb691514ee1cc5b.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

覆盖率可以导出，格式是html，可以直接使用浏览器查看，十分方便。

>另外也有使用Jacoco做代码覆盖率的，使用起来需要配置gradle，在Activity的oncreate和onDestroy方法中做写入处理，同时还要配置写入权限等，所以个人认为比较麻烦，没用Android studio这个自带的覆盖率使用方便。当然如果有兴趣的可以研究下,提供两个参考地址：[Jacoco插件](https://wiki.jenkins.io/display/JENKINS/JaCoCo+Plugin)、[Android studio中使用Jacoco](https://blog.csdn.net/LZN51/article/details/71499713)
