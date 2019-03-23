package ru.skillbench.tasks.text;
public class Main {
	public static void main(String[] args) {
		ContactCard cc = new ContactCardImpl();
		cc.getInstance("BEGIN:VCARD\r\nFN:Forrest Gump\r\nORG:Bubba Gump Shrimp Co.\r\nGENDER:\r\nBDAY:06-06-1944\r\nTEL;TYPE=WORK,VOICE:4951234567\r\nTEL;TYPE=CELL,VOICE:9150123456\r\nEND:VCARD");
		//System.out.println(cc.getFullName());
		//System.out.println(cc.getOrganization());
		System.out.println(cc.isWoman());
		//System.out.println(cc.getBirthday());
		//System.out.println(cc.getAgeYears());
		//System.out.println(((ContactCardImpl) cc).birthday.get(Calendar.YEAR));
		//System.out.println(((ContactCardImpl) cc).birthday.get(Calendar.MONTH));
		//System.out.println(((ContactCardImpl) cc).birthday.get(Calendar.DAY_OF_MONTH));
		//System.out.println(cc.getAge().getYears());
		System.out.println(cc.getPhone("CELL"));


	}
}
