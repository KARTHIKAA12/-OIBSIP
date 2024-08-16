/*
 atm project
*/
import java.util.Scanner;
public class atmproject{
    public static int takenIntegerasInput(int limit){
        int input = 0;
        boolean flag = false;
        while(!flag){
            try{
                Scanner sc = new Scanner(System.in);
                input = sc.nextInt();
                flag = true;
                if(flag && input>limit || input<1){
                    System.out.println("\nChoose the number between 1 to "+limit);
                    flag = false;
                }
            }catch(Exception e){
                System.out.println("Enter only integer value.");
                flag = false;
            }
        }
        return input;
    }
    public static void main(String[] args){
        System.out.println("\n ``````````````WELCOME TO KVB ATM INTERFACE``````````");
        System.out.println("\n1.Register \n2.Exit");
        System.out.println("Choose one option : ");
        int choose = takenIntegerasInput(2);
        if(choose == 1){
            Account_details a = new Account_details();
            a.registeration();
            while(true){
                System.out.println("\n1.Login \n2.Exit");
                System.out.println("Enter your Choice : ");
                int ch = takenIntegerasInput(2);
                if(ch==1){
                    if(a.login()){
                        System.out.println("\n **********WELCOME BACK"+a.name+"***********");
                        boolean isFinished = false;
                        while(!isFinished){
                        System.out.println("\n1.Withdraw \n2.Deposit \n3.Transfer \n4.Check Balance \n5.Transaction History \n6.Exit");
                        System.out.println("Enter your choice: ");
                        int c = takenIntegerasInput(6);
                        switch(c){
                            case 1:
                                a.withdraw();
                                break;
                            case 2:
                                a.deposit();
                                break;
                            case 3:
                                a.transfer();
                                break;
                            case 4:
                                a.checkBalance();
                                break;
                            case 5:
                                a.transactHistory();
                                break;
                            case 6:
                                isFinished = true;
                                break;
                            }
                        }
                    }
                }else{
                    System.exit(0);
                }
            }
        }else{
            System.exit(0);
        }
    }
}

class Account_details{
    String name;
    String username;
    String password;
    String account_no;
    float balance = 10000f;
    int transactions = 0;
    String transaction_history = "";

    public void registeration(){
        Scanner sc = new Scanner(System.in);
        System.out.println("\nEnter your Name : ");
        this.name = sc.nextLine();
        System.out.println("\nEnter your Username : ");
        this.username = sc.nextLine();
        System.out.println("\nEnter your Password : ");
        this.password = sc.nextLine();
        System.out.println("\nEnter your Account Number : ");
        this.account_no = sc.nextLine();
        System.out.println("Registered Successfully! you can log into your Account !");
    }
    public boolean login(){
        boolean isLogin = false;
        Scanner sc = new Scanner(System.in);
        while( !isLogin){
            System.out.println("\nEnter your Username : ");
            String Username = sc.nextLine();
            if(Username.equals(username)){
                while(!isLogin){
                    System.out.println("Enter your Password : ");
                    String Password = sc.nextLine();
                    if(Password.equals(password)){
                        System.out.println("\nLogin Done Successfully! ");
                        isLogin = true;
                    }
                    else{
                        System.out.println("\nPassword incorrect.");
                    }
                }
            }
            else{
                System.out.println("Username not found!");
            }
        }
        return isLogin;
    }
    public void withdraw(){
        System.out.println("\n WITHDRAWAL STARTS ");
        System.out.println("\n Enter the Amount for Withdrawal : ");
        Scanner sc = new Scanner(System.in);
        float amount = sc.nextFloat();
        try{
            if(balance >= amount){
                transactions++;
                balance -= amount;
                System.out.println("\nWithdrawal Successful");
                String str = amount + "Rs Withdrawn\n";
                transaction_history = transaction_history.concat(str);
            }else{
                System.out.println("\nbalance insufficient.");
            }
        }catch(Exception e){

        }
    }
    public void deposit(){
        System.out.println("\n DEPOSITION STARTS! ");
        System.out.println("\n Enter the Amount for Deposition : ");
        Scanner sc = new Scanner(System.in);
        float amount = sc.nextFloat();
        try{
            if(balance <= amount){
                transactions++;
                balance += amount;
                System.out.println("\nSuccessfully Deposited");
                String str = amount + "Rs Deposited\n";
                transaction_history = transaction_history.concat(str);
            }else{
                System.out.println("\nSorry. The limit is 50000.");
            }
        }catch(Exception e){
          
        }
    }
    public void transfer(){
        System.out.println("\n TRANSFERING STARTS ");
        Scanner sc = new Scanner(System.in);
        System.out.println("\n Enter the Recipient's Name : ");
        String recipient = sc.nextLine();
        System.out.println("\nEnter the Amount to transfer : ");
        float amount = sc.nextFloat();
        try{
            if(balance >= amount){
            if(amount <= 50000f){
                transactions++;
                balance -= amount;
                System.out.println("\nSuccessfully Transfered to "+ recipient);
                String str = amount + "Rs transferred to" + recipient+"\n";
                transaction_history = transaction_history.concat(str);
            }else{
                System.out.println("\nSorry. The limit is 50000.");
            }
            }else{
                System.out.println("\nbalance insufficient.");
            }
        }catch(Exception e){
            
        }
    }
    public void checkBalance(){
        System.out.println("\n your balance is : "+balance+"Rs");
    }
    public void transactHistory(){
        if(transactions == 0){
            System.out.println("\n No transactions.");
        }
        else{
            System.out.println("\nTRANSACTION HISTORY : "+transaction_history);
        }
    }
}
