/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import java.util.Comparator;

/**
 *
 * @author Asus
 */
public class NewsComparator implements Comparator<NewsEntity2> {

    public NewsComparator() {
    }

    @Override
    public int compare(NewsEntity2 o1, NewsEntity2 o2) {
        return o2.getDate().compareTo(o1.getDate()); //Sort descending
    }
    
}
