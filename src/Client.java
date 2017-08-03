import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 * 租户
 */
public class Client {

    public static void main(String[] args) {
        //我们要代理的真实对象--房东
        HouseOwner houseOwner = new HouseOwner();

        //我们要代理哪个真实对象，就将该对象传进去，最后通过该真实对象来调用其方法

        InvocationHandler handler = new DynamicProxy(houseOwner);

        /**
         *通过Proxy的newProxyInstance方法来创建我们的代理对象，我们来看看其三个参数
         * 第一个参数 handler.getClass.getClassLoader 我们这里使用handler这个类的ClassLoader对象来加载我们的代理对象
         * 第二个参数 houseOwner.getClass.getInterfaces(),我们这里为代理对象提供的接口是真实对象所实行的接口，表示我要代理的是该真实对象，这样我们就可以调用接口里面的方法了
         * 第三个参数 handler,我们这里将这个代理对象关联到了上方的InvocationHandler这个对象上
         */

        //生成一个动态代理类，中介
        RentHouse rentHouse = (RentHouse) Proxy.newProxyInstance(handler.getClass().getClassLoader(),houseOwner.getClass().getInterfaces(),handler);

        System.out.println(rentHouse.getClass().getName());

        //调用房屋出租
        rentHouse.rent();

        //付费给中介
        rentHouse.charge("100");
    }
}
