package com.example.springsecurity_authority.querydsl;

import com.example.springsecurity_authority.dto.GroupSearchCriteria;
import com.example.springsecurity_authority.entity.Group;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import static com.example.springsecurity_authority.entity.QGroup.group;

@Repository
@RequiredArgsConstructor
public class GroupQuerydslCustom {
    private final JPAQueryFactory queryFactory;

    private void tmp11() {

    }


    public List<Group> findGroups(GroupSearchCriteria searchCriteria) {

        BooleanExpression likeGroupName = likeGroupName(searchCriteria.getGroupname());
        BooleanExpression eqTag = eqTag(searchCriteria.getTag());
        BooleanExpression eqOwnerId = eqOwnerId(searchCriteria.getOwnerId());


        return queryFactory
            .selectFrom(group) // null인 BooleanExpression은 검색조건에서 무시된다.
            .where(likeGroupName,eqTag,eqOwnerId)
            .fetch();

    }

    private BooleanExpression eqOwnerId(String ownerId) {
        if (ownerId == null) {
            return null;
        }
        return group.ownerId.eq(ownerId);
    }

    private BooleanExpression eqTag(String tag) {
        if (tag == null) {
            return null;
        }
        return group.tag.eq(tag);
    }

    private BooleanExpression likeGroupName(String groupname) {
        if (groupname == null) {
            return null;
        }
        return group.groupname.contains(groupname);
    }


}
