package com.lucas.security.uuid;

import static java.nio.charset.StandardCharsets.UTF_8;

import java.io.Serializable;
import java.util.UUID;

import org.bouncycastle.util.Arrays;
import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

import com.lucas.security.models.User;
import com.mchange.lang.LongUtils;

public class UuidGenerator implements IdentifierGenerator {

    public static final UUID zeroUuid = new UUID(0,0);

    @Override
    public Serializable generate(final SharedSessionContractImplementor sharedSessionContractImplementor, final Object object)
            throws HibernateException {

        final User user = (User) object;

        return generateType3UUID(zeroUuid, user.getEmail());
    }

    /**
     * Given a namespace and a name, generates a type 3 UUID. This method makes it possible to map Ids that can be
     * scoped to an UUID.
     *
     * @param namespace The namespace used to generate the UUID.
     * @param nameOrId The name or local Id relative to the name space used.g
     */
    public static UUID generateType3UUID(final UUID namespace, final String nameOrId) {

        final byte[] namespaceMsb = LongUtils.byteArrayFromLong(namespace.getMostSignificantBits());
        final byte[] namespaceLsb = LongUtils.byteArrayFromLong(namespace.getLeastSignificantBits());
        final byte[] nameOrIdBytes = nameOrId.getBytes(UTF_8);
        final byte[] result = Arrays.concatenate(namespaceMsb, namespaceLsb, nameOrIdBytes);

        return UUID.nameUUIDFromBytes(result);
    }
}
