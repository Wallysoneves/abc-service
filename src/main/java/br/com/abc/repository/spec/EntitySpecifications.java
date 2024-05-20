package br.com.abc.repository.spec;

import br.com.abc.core.BaseEntity;
import org.springframework.data.jpa.domain.Specification;

public class EntitySpecifications {

    public static <T extends BaseEntity> Specification<T> notDeleted() {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.isFalse(root.get("deleted"));
    }
}
