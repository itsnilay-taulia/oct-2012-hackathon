package com.taulia.hackathon.data;

import com.taulia.hackathon.rest.EarlyPaymentRestHelper;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by IntelliJ IDEA.
 * User: nilaypatel
 * Date: 9/27/12
 * Time: 2:35 PM
 * To change this template use File | Settings | File Templates.
 */
public class EPInvItems {
     private static ArrayList<String> epInvItems = new ArrayList<String>();

	public EPInvItems() {
//		epInvItems = DataBaseHelper.queryDB("type = 'ep_list'");
        epInvItems.clear();
        epInvItems.add("Inv1 - $1000");
        epInvItems.add("Inv2 - $5000");
        epInvItems.add("Inv3 - $5000");
        epInvItems.add("Inv4 - $7000");
		Collections.sort(epInvItems);
	}

	public static void setEPInvItems(ArrayList<String> epInvItems) {
		EPInvItems.epInvItems = epInvItems;
	}

	/**
	 * @return the epInvItems
	 */
	public static ArrayList<String> getEPInvItems() {

		return epInvItems;
	}

	public static String getEPInvItem(int index) {
		return epInvItems.get(index);
	}

    public static void main(String[] args){
          EPInvItems epInvItems = new EPInvItems();
//          System.out.print(epInvItems.getEPInvItems());
          System.out.print(EarlyPaymentRestHelper.getListOfEarlyPayableInvoices().size());

    }
}
