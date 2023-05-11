import java.math.BigDecimal;

/**
 * Студент
 */
public class Student {

    private Integer id;
    private String name;
    private BigDecimal amount;

    public Student(Integer id, String name, BigDecimal amount){
        this.id = id;
        this.name = name;
        this.amount = amount;
    }

    public Integer getId(){
        return id;
    }

    public void setId(Integer id){
        this.id = id;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    @Override
    public boolean equals(Object obj){
        if (obj == null){
            return false;
        }
        if (obj == this){
            return true;
        }
        if (!(obj instanceof Student)){
            return false;
        }
        Student s = (Student)obj;
        return this.hashCode() == s.hashCode();

    }

    @Override
    public int hashCode(){
        return id + name.hashCode();
    }

    @Override
    public String toString() {
        return String.format("id: %d, name: %s, amount: %.2f", id, name, amount);
    }
}
