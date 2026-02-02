package goodspace.bllsoneshot.entity.assignment

import goodspace.bllsoneshot.entity.BaseEntity
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne

@Entity
class ColumnLink(
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    val task: Task,

    val link: String
) : BaseEntity()
