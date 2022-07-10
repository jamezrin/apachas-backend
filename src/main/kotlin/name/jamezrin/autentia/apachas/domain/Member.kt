package name.jamezrin.autentia.apachas.domain

import com.fasterxml.jackson.annotation.JsonFormat
import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import com.fasterxml.jackson.databind.annotation.JsonSerialize
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer
import io.micronaut.core.annotation.Introspected
import org.hibernate.annotations.CreationTimestamp
import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(name = "apachas_person")
@Introspected
class Member(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    @Column
    var name: String,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "group_id", nullable = false)
    @field:JsonIgnore
    var group: Group?,

    @OneToMany(mappedBy = "person", fetch = FetchType.EAGER, cascade = [CascadeType.ALL], orphanRemoval = true)
    @field:JsonInclude
    var expenses: MutableList<Expense> = mutableListOf(),

    @CreationTimestamp
    @field:JsonDeserialize(using = LocalDateTimeDeserializer::class)
    @field:JsonSerialize(using = LocalDateTimeSerializer::class)
    @field:JsonFormat(shape = JsonFormat.Shape.STRING)
    var createdAt: LocalDateTime? = LocalDateTime.now(),
)
