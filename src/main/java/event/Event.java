package event;

import generic.StringTool;

import java.util.Date;



public class Event {

    private int id;
    private String titre;
    private Date date;
    private String description;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    //---------------------------------------------------
    public boolean titreIsValid() {
		return !StringTool.isNullOrEmpty(getTitre());
	}   
}
