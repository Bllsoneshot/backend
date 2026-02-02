package goodspace.bllsoneshot.entity.user

import goodspace.bllsoneshot.entity.BaseEntity
import goodspace.bllsoneshot.entity.assignment.Subject
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.FetchType
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne

@Entity
class MenteeSubject(
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    val mentee: User,

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    val subject: Subject
) : BaseEntity()
