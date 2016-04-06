package com.jurin_n.domain.model.practice.member;

public class PracticeMemberBuilder {
    private PracticeMemberId practiceMemberId;
    private String name;

    public static PracticeMemberBuilder anMember() {
        return new PracticeMemberBuilder();
    }

    public PracticeMemberBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public PracticeMemberBuilder withPracticeMemberId(
            PracticeMemberId practiceMemberId) {
        this.practiceMemberId = practiceMemberId;
        return this;
    }

    public PracticeMember build() {
        PracticeMember member = new PracticeMember(practiceMemberId, name);
        return member;
    }
}
