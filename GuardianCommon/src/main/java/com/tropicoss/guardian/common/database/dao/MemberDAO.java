package com.tropicoss.guardian.common.database.dao;

import com.tropicoss.guardian.common.database.model.Member;

import java.util.List;

public interface MemberDAO {
    void addMember(Member member);
    Member getMemberById(int memberId);
    List<Member> getAllMembers();
    void updateMember(Member member);
    void deleteMember(int memberId);
}
