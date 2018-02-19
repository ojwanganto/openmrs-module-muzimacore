package org.openmrs.module.muzima.web.controller;

import org.javarosa.xform.parse.ValidationMessages;
import org.openmrs.api.context.Context;
import org.openmrs.module.muzima.api.service.MuzimaFormService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

@Controller
@RequestMapping(value = "module/muzimacore")

public class JavaRosaFormUploadController {


    @ResponseBody
    @RequestMapping(value = "/javarosa/validate.form", method = RequestMethod.POST)
    public ValidationMessages validateJavaRosa(final MultipartHttpServletRequest request) throws Exception {
        MuzimaFormService service = Context.getService(MuzimaFormService.class);
        return service.validateJavaRosa(extractFile(request));
    }

    @ResponseBody
    @RequestMapping(value = "/odk/validate.form", method = RequestMethod.POST)
    public ValidationMessages validateODK(final MultipartHttpServletRequest request) throws Exception {
        MuzimaFormService service = Context.getService(MuzimaFormService.class);
        return service.validateODK(extractFile(request));
    }

    @ResponseBody
    @RequestMapping(value = "/javarosa/upload.form", method = RequestMethod.POST)
    public void uploadJavaRosa(final MultipartHttpServletRequest request,
                               final @RequestParam String form,
                               final @RequestParam String discriminator) throws Exception {
        if (Context.isAuthenticated()) {
            MuzimaFormService service = Context.getService(MuzimaFormService.class);
            service.create(extractFile(request), form, discriminator);
        }
    }

    @ResponseBody
    @RequestMapping(value="/javarosa/update.form", method = RequestMethod.POST)
    public  void updateJavaRosa(final MultipartHttpServletRequest request,
                                final @RequestParam String form_id) throws Exception {
        if (Context.isAuthenticated()) {
            MuzimaFormService service = Context.getService(MuzimaFormService.class);
            service.update(extractFile(request), form_id);
        }

    }

    @ResponseBody
    @RequestMapping(value = "/html/upload.form", method = RequestMethod.POST)
    public void uploadHTMLForm(final MultipartHttpServletRequest request,
                               final @RequestParam String form,
                               final @RequestParam String discriminator) throws Exception {
        if (Context.isAuthenticated()) {
            MuzimaFormService service = Context.getService(MuzimaFormService.class);
            service.createHTMLForm(extractFile(request), form, discriminator);
        }
    }

    @ResponseBody
    @RequestMapping(value = "/html/update.form", method = RequestMethod.POST)
    public void updateHTMLForm(final MultipartHttpServletRequest request,
                               final @RequestParam String form) throws Exception {
        if (Context.isAuthenticated()) {
            MuzimaFormService service = Context.getService(MuzimaFormService.class);
            service.updateHTMLForm(extractFile(request), form);
        }
    }

    @ResponseBody
    @RequestMapping(value = "/odk/upload.form", method = RequestMethod.POST)
    public void uploadODK(final MultipartHttpServletRequest request,
                          final @RequestParam String form,
                          final @RequestParam String discriminator) throws Exception {
        if (Context.isAuthenticated()) {
            MuzimaFormService service = Context.getService(MuzimaFormService.class);
            service.importODK(extractFile(request), form, discriminator);
        }
    }

    private String extractFile(final MultipartHttpServletRequest request) throws Exception {
        MultipartFile file = request.getFile("file");
        return readStream(file.getInputStream());
    }

    private String readStream(final InputStream stream) throws IOException {
        return new Scanner(stream, "UTF-8").useDelimiter("\\A").next();
    }

}
