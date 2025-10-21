package com.example.umc9th.domain.review.repository;

import com.example.umc9th.domain.review.entity.QReply;
import com.example.umc9th.domain.review.entity.QReview;
import com.example.umc9th.domain.review.entity.Review;
import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class ReviewQueryDslImpl implements ReviewQueryDsl {
    private final EntityManager em;

    // 내가 작성한 리뷰 조회 API(가게별, 별점대별)
    @Override
    public List<Review> searchReview(Predicate predicate) {
        JPAQueryFactory queryFactory = new JPAQueryFactory(em);

        // Q 클래스 선언
        QReview review = QReview.review;
        QReply reply = QReply.reply;

        //review와 reply left outer join
        return queryFactory
                .selectFrom(review)
                .leftJoin(review.reply, reply).fetchJoin()
                .where(predicate)
                .fetch();
    }
}
