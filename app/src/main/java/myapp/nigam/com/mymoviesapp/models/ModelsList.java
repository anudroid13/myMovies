package myapp.nigam.com.mymoviesapp.models;

import java.util.ArrayList;

public class ModelsList {

    private ArrayList<MovieDetails> arrayList;

    public ArrayList<MovieDetails> getArrayList() {
        return arrayList;
    }

    public void setArrayList(ArrayList<MovieDetails> arrayList) {
        this.arrayList = arrayList;
    }

    private static ModelsList modelsList;

    private ModelsList() {
    }

    public static ModelsList getInstance() {

        if (modelsList == null) {
            modelsList = new ModelsList();
        }
        return modelsList;
    }
}
