import java.io.Serializable;

/**
 * @author: create by suhy
 * @version: v1.0
 * @description: Serializable
 * @className: Address_Serializable
 * @date:2021/8/30 14:07
 */

/**
 * 序列化和反序列化的作用，Address_Serializable实现Serializable
 */
public class Address_Serializable implements Serializable {
    private static final long serialVersionUID = 4528562278030001767L;
    String street;
    String country;

    public void setStreet(String street) {
        this.street = street;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getStreet() {
        return this.street;
    }

    public String getCountry() {
        return this.country;
    }

    @Override
    public String toString() {
        return new StringBuffer(" Street : ")
                .append(this.street)
                .append(" Country : ")
                .append(this.country).toString();
    }
}
