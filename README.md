# dynamicProxy
动态代理<br/>
1.AOP-面向切面编程，程序解耦<br/>
    简言之当你想要对一些类的内部的一些方法，在执行前和执行后做一些共同的操作，而在方法中执行个性化操作的时候---用动态代理。在业务量庞大的时候能够降低代码量，增强可维护性。
<br/><br/>
2.想要自定义第三方类库中的某些方法<br/>
    我引用了一个第三方类库，但他的一些方法不满足我的需求，我想自己重写以下那几个方法，或在方法前后加一些特殊的操作—用动态代理。但需要注意的是，这些方法有局限性。
    
3.什么是动态代理<br/>

假如你是一个大房东（被代理人），你有很多套房子想要出租，而你觉得找租客太麻烦，不愿意自己弄，因而你找一个人来代理你（代理人），帮打理这些东西，而这个人（代理人也就是中介）在帮你出租房屋的时候对你收取一些相应的中介费（对房屋出租的一些额外操作）。对于租客而言，中介就是房东，代理你做一些事情。

以上，就是一个代理的例子，而他为什么叫动态代理，“动态”两个字体现在什么地方？

我们可以这样想，如果你的每一套房子你都请一个代理人帮你打理，每当你想再出租一套房子的时候你得再请一个，这样你会请很多的代理人，花费高额的中介成本，这可以看作常说的“静态代理”。

但假如我们把所有的房子都交给一个中介来代理，让他在多套房子之间动态的切换身份，帮你应付每一个租客。这就是一个“动态代理”的过程。动态代理的一大特点就是编译阶段没有代理类在运行时才生成代理类。




4.动态代理该如何使用

在java的动态代理机制中，有两个重要的类和接口，一个是 InvocationHandler(Interface)、另一个则是 Proxy(Class)，这一个类和接口是实现我们动态代理所必须用到的。

每一个动态代理类都必须要实现InvocationHandler这个接口（代码中的中介），并且每个代理类的实例都关联到了一个handler，当我们通过代理对象调用一个方法的时候，这个方法的调用就会被转发为由InvocationHandler这个接口的 invoke（对方法的增强就写在这里面） 方法来进行调用。

Object invoke(Object proxy, Method method, Object[] args) throws Throwable
我们看到这个方法一共接受三个参数，那么这三个参数分别代表什么呢？

Object invoke(Object proxy, Method method, Object[] args) throws Throwable
//proxy:　　指代我们所代理的那个真实对象
//method:　　指代的是我们所要调用真实对象的某个方法的Method对象
//args:　　指代的是调用真实对象某个方法时接受的参数
接下来我们来看看Proxy这个类

public static Object newProxyInstance(ClassLoader loader, Class<?>[] interfaces,  InvocationHandler h)  throws IllegalArgumentException
Proxy这个类的作用就是用来动态创建一个代理对象的类，它提供了许多的方法，但是我们用的最多的就是 newProxyInstance 这个方法：

public static Object newProxyInstance(ClassLoader loader, Class<?>[] interfaces,  InvocationHandler h)  throws IllegalArgumentException
这个方法的作用就是得到一个动态的代理对象，其接收三个参数，我们来看看这三个参数所代表的含义

public static Object newProxyInstance(ClassLoader loader, Class<?>[] interfaces, InvocationHandler h) throws IllegalArgumentException

//loader:　　一个ClassLoader对象，定义了由哪个ClassLoader对象来对生成的代理对象进行加载
//interfaces:　　一个Interface对象的数组，表示的是我将要给我需要代理的对象提供一组什么接口，如果我提供了一组接口给它，那么这个代理对象就宣称实现了该接口(多态)，这样我就能调用这组接口中的方法了
//h:　　一个InvocationHandler对象，表示的是当我这个动态代理对象在调用方法的时候，会关联到哪一个InvocationHandler对象上
这样一来，结合上面给出的代码，我们就可以明白动态代理的使用方法了

5.动态代理的局限性

从动态代理的使用方法中我们看到其实可以被增强的方法都是实现了借口的（不实现借口的public方法也可以通过继承被代理类来使用），代码中的HouseOwner继承了RentHouse 。而对于private方法JDK的动态代理无能为力！
以上的动态代理是JDK的，对于java工程还有大名鼎鼎的CGLib，但遗憾的是CGLib并不能在android中使用，android虚拟机相对与jvm还是有区别的。
    
    
    
    
