package name.jamezrin.autentia.apachas.domain

import com.fasterxml.jackson.annotation.JsonFormat
import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import com.fasterxml.jackson.databind.annotation.JsonSerialize
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer
import io.micronaut.core.annotation.Introspected
import io.micronaut.data.annotation.DateCreated
import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(name = "apachas_expense")
@Introspected
class GroupExpense(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    @Column
    var amount: Double,

    @Column
    var description: String,

    @Column
    @field:JsonDeserialize(using = LocalDateTimeDeserializer::class)
    @field:JsonSerialize(using = LocalDateTimeSerializer::class)
    @field:JsonFormat(shape = JsonFormat.Shape.STRING)
    var expenseAt: LocalDateTime,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "person_id", nullable = false)
    @field:JsonIgnore
    var person: GroupMember?,

    @DateCreated
    @field:JsonDeserialize(using = LocalDateTimeDeserializer::class)
    @field:JsonSerialize(using = LocalDateTimeSerializer::class)
    @field:JsonFormat(shape = JsonFormat.Shape.STRING)
    var createdAt: LocalDateTime? = LocalDateTime.now(),
)
