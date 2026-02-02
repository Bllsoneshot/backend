package goodspace.bllsoneshot.entity.assignment

import goodspace.bllsoneshot.entity.BaseEntity
import jakarta.persistence.Column
import jakarta.persistence.Entity

@Entity
class GeneralComment(
    @Column(nullable = false)
    val content: String
) : BaseEntity()
