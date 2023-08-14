package org.ilastik.ilastik4ij.workflow;

import net.imagej.Dataset;
import net.imglib2.type.NativeType;
import org.scijava.command.Command;
import org.scijava.plugin.Parameter;
import org.scijava.plugin.Plugin;

import java.util.Collections;
import java.util.Map;

@Plugin(type = Command.class, headless = true, menuPath = "Plugins>ilastik>Run Object Classification Prediction")
public final class ObjectClassificationCommand<T extends NativeType<T>> extends WorkflowCommand<T> {
    @Parameter(label = "Pixel Probability or Segmentation image")
    public Dataset inputProbOrSegImage;

    @Parameter(label = "Second Input Type", choices = {ROLE_PROBABILITIES, ROLE_SEGMENTATION}, style = "radioButtonHorizontal")
    public String secondInputType = ROLE_PROBABILITIES;

    @Override
    protected Map<String, Dataset> workflowInputs() {
        if (ROLE_PROBABILITIES.equals(secondInputType)) {
            return Collections.singletonMap("prediction_maps", inputProbOrSegImage);
        }
        if (ROLE_SEGMENTATION.equals(secondInputType)) {
            return Collections.singletonMap("segmentation_image", inputProbOrSegImage);
        }
        throw new IllegalStateException("Unexpected value: " + secondInputType);
    }
}