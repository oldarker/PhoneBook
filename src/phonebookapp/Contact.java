
package phonebookapp;

public class Contact {
    private String name;
    private String number;
    
    public Contact (String name, String number)
    {
        this.name = name;
        this.number = number;
    }
    
    public String getName() { return name; }
    public String getPhone() { return number; }
}
