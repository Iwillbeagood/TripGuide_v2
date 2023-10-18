package org.gradle.accessors.dm;

import org.gradle.api.NonNullApi;
import org.gradle.api.artifacts.ProjectDependency;
import org.gradle.api.internal.artifacts.dependencies.ProjectDependencyInternal;
import org.gradle.api.internal.artifacts.DefaultProjectDependencyFactory;
import org.gradle.api.internal.artifacts.dsl.dependencies.ProjectFinder;
import org.gradle.api.internal.catalog.DelegatingProjectDependency;
import org.gradle.api.internal.catalog.TypeSafeProjectDependencyFactory;
import javax.inject.Inject;

@NonNullApi
public class FeatureProjectDependency extends DelegatingProjectDependency {

    @Inject
    public FeatureProjectDependency(TypeSafeProjectDependencyFactory factory, ProjectDependencyInternal delegate) {
        super(factory, delegate);
    }

    /**
     * Creates a project dependency on the project at path ":feature:addtravel"
     */
    public Feature_AddtravelProjectDependency getAddtravel() { return new Feature_AddtravelProjectDependency(getFactory(), create(":feature:addtravel")); }

    /**
     * Creates a project dependency on the project at path ":feature:main"
     */
    public Feature_MainProjectDependency getMain() { return new Feature_MainProjectDependency(getFactory(), create(":feature:main")); }

    /**
     * Creates a project dependency on the project at path ":feature:mytravel"
     */
    public Feature_MytravelProjectDependency getMytravel() { return new Feature_MytravelProjectDependency(getFactory(), create(":feature:mytravel")); }

    /**
     * Creates a project dependency on the project at path ":feature:recommend"
     */
    public Feature_RecommendProjectDependency getRecommend() { return new Feature_RecommendProjectDependency(getFactory(), create(":feature:recommend")); }

    /**
     * Creates a project dependency on the project at path ":feature:setting"
     */
    public Feature_SettingProjectDependency getSetting() { return new Feature_SettingProjectDependency(getFactory(), create(":feature:setting")); }

}
