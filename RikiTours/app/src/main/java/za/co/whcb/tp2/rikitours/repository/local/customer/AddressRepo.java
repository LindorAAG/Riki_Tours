package za.co.whcb.tp2.rikitours.repository.local.customer;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

import za.co.whcb.tp2.rikitours.common.Converter;
import za.co.whcb.tp2.rikitours.config.database.Database;
import za.co.whcb.tp2.rikitours.config.database.table.common.CountryTable;
import za.co.whcb.tp2.rikitours.config.database.table.customer.AddressTable;
import za.co.whcb.tp2.rikitours.domain.customer.CustomerAddress;
import za.co.whcb.tp2.rikitours.factories.customer.AddressFactory;
import za.co.whcb.tp2.rikitours.factories.tour.CountryFactory;

/**
 * Created by Game330 on 2016-10-14.
 */
public class AddressRepo extends SQLiteOpenHelper {

    private SQLiteDatabase localDatabase;
    private ContentValues contentValues;
    private static AddressTable addressTable;


    public AddressRepo(Context context) {
        super(context, Database.name, null, Database.version);
        addressTable = new AddressTable();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            final String query = Converter.toCreateTableQuery(addressTable.getTableName(),addressTable.getAllAttributes());
            db.execSQL(query);
        }
        catch (Exception ex) {
            Log.d("SQL ERROR", ex.getMessage());

        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXITS " + addressTable.getTableName());
        onCreate(db);
    }

    public boolean addCustomerAddress(CustomerAddress address ) {
        long returned ;
        localDatabase = this.getWritableDatabase();
        addressTable  = new AddressTable();
        contentValues = new ContentValues();

        contentValues.put(addressTable.getAttributeId().name, address.getId());
        contentValues.put(addressTable.getAttributeStreetNo().name, address.getStreet_no());
        contentValues.put(addressTable.getAttributeSurbub().name, address.getSuburb());
        contentValues.put(addressTable.getAttributeHouseNo().name, address.getHouse_no());
        contentValues.put(addressTable.getAttributePostalCode().name, address.getPostalCode());

        try {
            returned = localDatabase.insert(addressTable.getTableName(), null, contentValues);
        }catch (Exception ex) {
            returned = 0;
            Log.d("exception ::::",ex.getMessage());

        }

        return (returned != -1) ? true : false;
    }

    public CustomerAddress findAddressById(long id) {
        CustomerAddress addressFound = null;
        localDatabase = this.getReadableDatabase();
        String query = Converter.toSelectAllWhere(addressTable.getTableName(),
                addressTable.getAttributeId(), String.valueOf(id));
        Cursor data = localDatabase.rawQuery(query, null);

        if(data.getCount() != 0) {
            while (data.moveToNext()) {
                addressFound = AddressFactory.getAddress(data.getLong(0), data.getString(1),
                        data.getString(2), data.getString(3),data.getString(3));
            }
        }
        return addressFound;
    }

    public ArrayList<CustomerAddress> getAllCountries() {
        ArrayList<CustomerAddress> addresses = new ArrayList<>();
        CustomerAddress addressFound = null;
        localDatabase = this.getReadableDatabase();
        String query = Converter.toSelectAll(addressTable.getTableName());

        Cursor data = localDatabase.rawQuery(query, null);

        if(data.getCount() != 0) {
            while (data.moveToNext()) {
                addressFound = AddressFactory.getAddress(data.getLong(0), data.getString(1),
                        data.getString(2), data.getString(3),data.getString(3));
                addresses.add(addressFound);
            }
        }

        return addresses;
    }

    public boolean updateAddress(CustomerAddress updatedCustomerAddress, long id) {

        long returned ;
        localDatabase = this.getWritableDatabase();
        contentValues = new ContentValues();

        contentValues.put(addressTable.getAttributeId().name, updatedCustomerAddress.getId());
        contentValues.put(addressTable.getAttributeStreetNo().name, updatedCustomerAddress.getStreet_no());
        contentValues.put(addressTable.getAttributeSurbub().name, updatedCustomerAddress.getSuburb());
        contentValues.put(addressTable.getAttributeHouseNo().name, updatedCustomerAddress.getHouse_no());
        contentValues.put(addressTable.getAttributePostalCode().name, updatedCustomerAddress.getPostalCode());

        try {

            returned =  localDatabase.update(addressTable.getTableName(),
                    contentValues,addressTable.getPrimaryKey().name + " = ?",
                    new String[]{String.valueOf(id)});

        } catch (Exception ex) {
            returned = 0;

        }

        return (returned != 0) ? true : false;
    }

    public boolean deleteById(long id) {

        long returned ;
        localDatabase = this.getWritableDatabase();

        try {

            returned =  localDatabase.delete(addressTable.getTableName(),
                    addressTable.getPrimaryKey().name + " = ?",
                    new String[]{String.valueOf(id)});

        } catch (Exception ex) {
            returned = 0;

        }

        return (returned != 0) ? true : false;

    }

}
