package se.diabol.jenkins.pipeline.model;

import com.google.common.collect.ImmutableList;
import org.kohsuke.stapler.export.Exported;
import org.kohsuke.stapler.export.ExportedBean;
import se.diabol.jenkins.pipeline.model.status.StatusFactory;

import java.util.List;
import java.util.Objects;

import static com.google.common.base.Objects.toStringHelper;

/**
 * @author Per Huss <mr.per.huss@gmail.com>
 */
@ExportedBean(defaultVisibility = 100)
public class Pipeline extends Component
{
    private List<Stage> stages;

    private String version;

    private String triggeredBy;

    private Pipeline()
    {
        super(null, null);
    }

    public Pipeline(String name, String version, String triggeredBy, List<Stage> stages)
    {
        super(name, StatusFactory.idle()); // todo: IDLE is cheating
        this.version = version;
        this.triggeredBy = triggeredBy;
        this.stages = ImmutableList.copyOf(stages);
    }

    @Exported
    public List<Stage> getStages()
    {
        return stages;
    }

    @Exported
    public String getVersion()
    {
        return version;
    }

    @Exported
    public String getTriggeredBy() {
        return triggeredBy;
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(super.hashCode(), stages);
    }

    @Override
    public boolean equals(Object o)
    {
        return o == this || o instanceof Pipeline && equals((Pipeline) o);
    }

    private boolean equals(Pipeline o)
    {
        return super.equals(o) && Objects.equals(stages, o.stages) && Objects.equals(version, o.version);
    }

    @Override
    public String toString()
    {
        return toStringHelper(this)
                .add("name", getName())
                .add("version", getVersion())
                .add("status", getStatus())
                .add("stages", getStages())
                .toString();
    }
}