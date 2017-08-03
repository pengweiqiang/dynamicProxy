import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Created by pengweiqiang on 2017/8/3.
 */
public class DynamicProxy implements InvocationHandler{

    private Object subject;//这个就是我们要代理的真实对象，即房东
    public DynamicProxy(Object subject){
        this.subject = subject;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        //在代理真实对象前我们可以添加一些自己的操作，中介收取中介费
        System.out.println("1.before  "+method.getName()+" house");

        System.out.println("Method: "+method.getName());
        //如果方法是charge 则中介收取100元中介费
        if(method.getName().equals("charge")){
            method.invoke(subject,args);
            System.out.println("房东（被代理对象）付100元给中介（即代理对象）");
        }else{
            //当代理对象调用真实对象的方法时，其会自动的跳转到代理对象关联的handler对象的invoke方法进行调用
            method.invoke(subject,args);
        }
        System.out.println("2.after "+method.getName()+" house");
        return null;
    }
}
