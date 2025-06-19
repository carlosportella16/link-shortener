package encurtador.adapter.out.persistence;

import encurtador.config.TableName;
import encurtador.core.domain.User;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbAttribute;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbSecondaryPartitionKey;

import java.time.LocalDateTime;
import java.util.UUID;

import static encurtador.config.Constants.EMAIL_INDEX;

@DynamoDbBean
@TableName(name = "tb_users")
public class UserEntity {
    private UUID userId;
    private String email;
    private String password;
    private String nickname;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public UserEntity() {
    }

    public static UserEntity fromDomain(User user) {
        var entity = new UserEntity();
        entity.setUserId(user.getUserId());
        entity.setEmail(user.getEmail());
        entity.setPassword(user.getPassword());
        entity.setNickname(user.getNickname());
        entity.setCreatedAt(user.getCreatedAt());
        entity.setUpdatedAt(user.getUpdatedAt());
        return entity;
    }

    @DynamoDbPartitionKey
    @DynamoDbAttribute("user_id")
    public UUID getUserId() {
        return userId;
    }

    @DynamoDbSecondaryPartitionKey(indexNames = EMAIL_INDEX)
    @DynamoDbAttribute("email")
    public String getEmail() {
        return email;
    }

    @DynamoDbAttribute("password")
    public String getPassword() {
        return password;
    }

    @DynamoDbAttribute("nickname")
    public String getNickname() {
        return nickname;
    }

    @DynamoDbAttribute("created_at")
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    @DynamoDbAttribute("updated_at")
    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public User toDomain() {
        return new User(
                this.userId,
                this.email,
                this.password,
                this.nickname,
                this.createdAt,
                this.updatedAt
        );
    }
}
