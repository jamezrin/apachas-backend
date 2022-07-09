package name.jamezrin.autentia.apachas.domain

import com.fasterxml.jackson.annotation.JsonFormat
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
@Table(name = "apachas_group", indexes = [
    Index(columnList = "name", name = "apachas_group_names")
])
@Introspected
class Group(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    @Column(nullable = false, unique = true)
    val name: String,

    @OneToMany(mappedBy = "group", fetch = FetchType.EAGER, cascade = [CascadeType.ALL], orphanRemoval = true)
    @field:JsonInclude
    var friends: MutableList<GroupMember> = mutableListOf(),

    @CreationTimestamp
    @field:JsonDeserialize(using = LocalDateTimeDeserializer::class)
    @field:JsonSerialize(using = LocalDateTimeSerializer::class)
    @field:JsonFormat(shape = JsonFormat.Shape.STRING)
    var createdAt: LocalDateTime? = LocalDateTime.now(),
)
