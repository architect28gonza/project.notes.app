package org.com.application.domain.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.com.application.domain.services.ListGroupServices;
import com.note.persistence.entitys.GroupEntity;
import org.com.application.persistence.model.NoteModel;
import org.com.application.domain.repository.GroupListRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@SuppressWarnings("unchecked")
@AllArgsConstructor
@ApplicationScoped
public class ListGroupImpl implements ListGroupServices {

    @PersistenceContext
    private EntityManager entityManager;

    private final GroupListRepository groupListRepository;

    @Override
    @Transactional
    public List<NoteModel> getNotes(long id) {
        GroupEntity group = this.groupListRepository.findById(id);
        if (group != null) {
            Query query = entityManager.createNativeQuery(this.getSql(group.getName()));
            List<Object[]> results = query.getResultList();
            return results.stream()
                    .map(result -> NoteModel.builder()
                            .note_1(((Number) result[0]).doubleValue())
                            .note_2(((Number) result[1]).doubleValue())
                            .note_3(((Number) result[2]).doubleValue())
                            .final_note(result[3] != null ? ((Number) result[3]).doubleValue() : 0.0)
                            .build())
                    .collect(Collectors.toList());
        }
        return null;
    }

    private String getSql(String table) {
        return "SELECT note_1, note_2, note_3, final_note FROM ".concat(table).concat(";");
    }

}
