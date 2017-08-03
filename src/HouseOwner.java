/**
 * Created by pengweiqiang on 2017/8/3.
 * 房东
 */
public class HouseOwner implements RentHouse{

    @Override
    public void rent() {
        System.out.println("出租房屋一间");
    }

    @Override
    public void charge(String money) {
        System.out.println("给中介出租费用"+money);
    }
}
