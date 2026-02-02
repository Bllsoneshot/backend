package goodspace.bllsoneshot.entity.assignment

import goodspace.bllsoneshot.entity.BaseEntity
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.FetchType
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.OneToOne

@Entity
class Comment(
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    val task: Task,

    // TODO: ProofShot과 Annotation의 null 여부는 일치해야 함
    @OneToOne(fetch = FetchType.LAZY)
    val proofShot: ProofShot?,
    @OneToOne(fetch = FetchType.LAZY)
    val commentAnnotation: CommentAnnotation?,

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    val type: CommentType,

    @Column(nullable = false)
    val content: String,
) : BaseEntity()
