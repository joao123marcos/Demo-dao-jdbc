package Model.Class;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Seller {
    
    private static DateTimeFormatter fmt = 
     DateTimeFormatter.ofPattern("dd/MM/yyyy");

    private Integer idSeller;
    private String nameSeller;
    private String emailSeller;
    private LocalDate birthDate;
    private Double salarySeller;

    private Department department;

    public Seller() {

    }

    public Seller(Integer idSeller, String nameSeller, 
        String emailSeller, LocalDate birthDate, Double salarySeller,
        Department department) {

        this.idSeller = idSeller;
        this.nameSeller = nameSeller;
        this.emailSeller = emailSeller;
        this.birthDate = birthDate;
        this.salarySeller = salarySeller;
        this.department = department;
    }

    public static DateTimeFormatter getFmt() {
        return fmt;
    }

    public static void setFmt(DateTimeFormatter fmt) {
        Seller.fmt = fmt;
    }

    public Integer getIdSeller() {
        return idSeller;
    }

    public void setIdSeller(Integer idSeller) {
        this.idSeller = idSeller;
    }

    public String getNameSeller() {
        return nameSeller;
    }

    public void setNameSeller(String nameSeller) {
        this.nameSeller = nameSeller;
    }

    public String getEmailSeller() {
        return emailSeller;
    }

    public void setEmailSeller(String emailSeller) {
        this.emailSeller = emailSeller;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public Double getSalarySeller() {
        return salarySeller;
    }

    public void setSalarySeller(Double salarySeller) {
        this.salarySeller = salarySeller;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((idSeller == null) ? 0 : idSeller.hashCode());
        result = prime * result + 
          ((nameSeller == null) ? 0 : nameSeller.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Seller other = (Seller) obj;
        if (idSeller == null) {
            if (other.idSeller != null)
                return false;
        } else if (!idSeller.equals(other.idSeller))
            return false;
        if (nameSeller == null) {
            if (other.nameSeller != null)
                return false;
        } else if (!nameSeller.equals(other.nameSeller))
            return false;
        return true;
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append("Id Seller: "+getIdSeller()+"\n");
        sb.append("Name Seller: "+getNameSeller()+"\n");
        sb.append("Email Seller: "+getEmailSeller()+"\n");
        sb.append("BirthDate Seller: "+fmt.format(getBirthDate())+"\n");
        sb.append("BaseSalary Seller U$: "+
         String.format("%.2f",getSalarySeller())+"\n");
        sb.append("Department Seller: "+getDepartment()+"\n"); 
        return sb.toString();
    } 

    

    

    
}
