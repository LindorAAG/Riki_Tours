package za.co.whcb.tp2.rikitours.database.tablestoquery.Tour;

import org.junit.Test;

import za.co.whcb.tp2.rikitours.common.Converter;
import za.co.whcb.tp2.rikitours.config.database.table.common.CountryTable;
import za.co.whcb.tp2.rikitours.config.database.table.tour.AttractionTable;

/**
 * Created by work on 10/10/2016.
 */
public class CountryTest {
    @Test
    public void testCountry() throws Exception {

        CountryTable attractionTable = new CountryTable();
        String query = Converter.toCreateTableQuery(attractionTable.getTableName(),attractionTable.getAllAttributes());
        System.out.println(query);
    }
}
