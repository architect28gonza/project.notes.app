package org.com.application.domain.services;

import java.util.List;
import org.com.application.persistence.model.NoteModel;

public interface ListGroupServices {
    
    public List<NoteModel> getNotes(long id);

}
