package com.project.dao;

import java.util.List;

import com.project.classes.Member;

public interface MemberDAO {
    void addMember(Member member);

    void updateMember(Member member);

    void deleteMember(int memberId);
    
    List<Member> getAllMembers();

}
