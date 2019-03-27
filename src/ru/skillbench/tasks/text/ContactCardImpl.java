package ru.skillbench.tasks.text;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Period;
import java.util.*;

public class ContactCardImpl implements ContactCard{
    private String fullName;
    private String organization;
    private String gender;
    private Calendar birthday;
    private ArrayList<String> phoneNumber = new ArrayList<>(0);
    private ArrayList<String> typeNumber = new ArrayList<>(0);;


    @Override
    public ContactCard getInstance(Scanner scanner){
        LinkedList<String> values = new LinkedList<String>();
        while (scanner.hasNextLine()) {
            values.add(scanner.nextLine());
        }
        if (values.size() < 4 || values.get(0).compareTo("BEGIN:VCARD")!=0 || values.get(1).length()<4 || values.get(2).length()<5 || values.get(values.size()-1).compareTo("END:VCARD")!=0) {
            throw new NoSuchElementException();
        }
        if (values.get(1).charAt(2)!=':' || values.get(2).charAt(3)!=':') {
            throw new InputMismatchException();
        }
        String[] fnString = values.get(1).split(":");
        String[] orgString = values.get(2).split(":");
        if(fnString[0].compareTo("FN")!=0 || orgString[0].compareTo("ORG")!=0){
            throw new InputMismatchException();
        }
        fullName=fnString[1];
        organization=orgString[1];
        /*for(int i=0;i<values.size();i++){
            System.out.println(values.get(i));
        }*/
        int itNum=0;
        if(values.size()>4){
            for(int j=3;j<values.size()-1;j++){
                String[] temp = values.get(j).split(":");
                if(temp[0].compareTo("GENDER")==0 && values.get(j).charAt(6)==':'){
                    if(temp[1].compareTo("M")==0){
                        gender="M";
                    } else if (temp[1].compareTo("F")==0){
                        gender="F";
                    } else if (temp.length==1){
                        gender="";
                    } else {
                        throw new InputMismatchException();
                    }
                }
                if(temp[0].compareTo("BDAY")==0 && values.get(j).charAt(4)==':'){
                    SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
                    String[] tempDate = temp[1].split("-");

                    try {
                        if(tempDate.length!=3 || Integer.parseInt(tempDate[0])>31 || (Integer.parseInt(tempDate[0])<1) || (Integer.parseInt(tempDate[1])>12) || (Integer.parseInt(tempDate[1])<1) || (Integer.parseInt(tempDate[2])>2018)){
                            throw new InputMismatchException();
                        }
                        Date date = sdf.parse(temp[1]);
                        Calendar calendar = sdf.getCalendar();
                        birthday = calendar;
                    } catch (ParseException e) {
                        throw new InputMismatchException();
                    }
                    //System.out.println(birthday.get(5) + "-" + (birthday.get(2)+1) + "-" + birthday.get(1));
                }
                if(temp[0].split("=").length>1){
                    String[] tempNum = temp[0].split("=");
                    if(tempNum[0].compareTo("TEL;TYPE")==0){
                        String reg = "^[a-zA-Z0-9]*$";
                        //Pattern pg = Pattern.compile(reg, Pattern.CASE_INSENSITIVE | Pattern.DOTALL);
                        //System.out.println(typeNumber);
                        if(temp[1].length()!=10 || !temp[1].matches(reg)){
                            throw new InputMismatchException();
                        }
                        //Matcher match = pg.matcher(temp[1]);
                        //System.out.println(match);
                        String[] tempType = tempNum[1].split(",");
                        boolean check = false;
                        for(int i=0; i<typeNumber.size();i++){
                            if(typeNumber.get(i).compareTo(tempType[0])==0){
                                check = true;
                            }
                        }
                        if (!check){
                            typeNumber.add(tempType[0]);
                            String num = temp[1];
                            phoneNumber.add("("+num.charAt(0)+num.charAt(1)+num.charAt(2)+") " +num.charAt(3)+num.charAt(4)+num.charAt(5)+"-"+num.charAt(6)+num.charAt(7)+num.charAt(8)+num.charAt(9) );
                        }


                    } else {
                        throw new InputMismatchException();
                    }
                }


            }
        }
        //System.out.println(typeNumber);
        return this;
    }
    @Override
    public ContactCard getInstance(String data){
        return this.getInstance(new Scanner(data));
    }
    public String getFullName(){
        return fullName;
    }
    public String getOrganization(){
        return organization;
    }
    public boolean isWoman(){
        return gender=="F";
    }
    public Calendar getBirthday(){
        if(birthday==null){
            throw new NoSuchElementException();
        }
        return birthday;
    }


    public Period getAge(){
        if(birthday==null){
            throw new NoSuchElementException();
        }
        int year = Calendar.getInstance().get(Calendar.YEAR);
        int byear = birthday.get(Calendar.YEAR);
        //System.out.println(year + " "+ byear);
        Period age = Period.ofYears(year - byear);
        return age;
    }

    public int getAgeYears(){
        if(birthday==null){
            throw new NoSuchElementException();
        }
        int year = Calendar.getInstance().get(Calendar.YEAR);
        int byear = birthday.get(Calendar.YEAR);
        int day = Calendar.getInstance().get(Calendar.DAY_OF_YEAR);
        int bday = birthday.get(Calendar.DAY_OF_YEAR);
        if(day<bday){
            return year - byear-1;
        }
        return year - byear;
    }

   public String getPhone(String type){
       for(int i=0;i<typeNumber.size();i++){
           if(typeNumber.get(i).compareTo(type)==0){
               return phoneNumber.get(i);
           }
       }
       throw new NoSuchElementException();
   }

    //}
}
