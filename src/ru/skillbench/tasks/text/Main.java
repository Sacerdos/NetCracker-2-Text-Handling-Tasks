package ru.skillbench.tasks.text;
public class Main {
	public static void main(String[] args) {
		ContactCard cc = new ContactCardImpl();
		cc.getInstance("BEGIN:VCARD\r\nFN:Forrest Gump\r\nORG:Bubba Gump Shrimp Co.\r\nGENDER:M\r\nBDAY:24-11-1991\r\nTEL;TYPE=HOME:1234567890\r\nEND:VCARD");
		//cc.getInstance("BEGIN:VCARD\r\nFN:Forest\r\nORG:Bubba Gump Shrimp Co.\r\nGENDER:M\r\nBDAY:32-58-1944\r\nEND:VCARD");
		System.out.println(cc.getFullName());
		System.out.println(cc.getOrganization());
		System.out.println(cc.isWoman());
		System.out.println(cc.getBirthday());
		System.out.println(cc.getAgeYears());
		System.out.println(cc.getAge());
		System.out.println(cc.getPhone("WORK"));


	}
}
