/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import java.util.Comparator;

public class NewsComparator implements Comparator<NewsEntity> {
    @Override
    public int compare(NewsEntity o1, NewsEntity o2) {
        return o2.getDate().compareTo(o1.getDate()); // Sort descending
    }
}

