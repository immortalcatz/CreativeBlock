/*
 * The MIT License (MIT)
 *
 * Copyright (c) dags <https://www.dags.me>
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package me.dags.creativeblock.app;

import me.dags.creativeblock.app.conversion.Conversion;
import me.dags.creativeblock.app.conversion.Conversions;
import me.dags.creativeblock.app.json.JObject;
import me.dags.creativeblock.app.util.UpdateListener;
import me.dags.creativeblock.app.util.Utils;
import me.dags.creativeblock.definition.BlockType;
import me.dags.creativeblock.definition.BlockTextures;
import me.dags.creativeblock.definition.serialize.DefinitionSerializable;
import me.dags.creativeblock.definition.serialize.MaterialSerializable;
import me.dags.creativeblock.util.LogUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

public class EditorPane extends JPanel
{
    private final File rootDir;
    private final File definitionsRoot;
    private final File blockTextures;
    private final String domain;

    private final boolean createDirs = true;
    private final int windowWidth = 400;
    private final int labelWidth = 80;
    private final int itemWidth = windowWidth - labelWidth;
    private final int lineHeight = 25;

    private final Dimension labelDims = new Dimension(labelWidth, lineHeight);

    private final JTextField nameField = new JTextField();
    private final JTextField categoryField = new JTextField();
    private final JComboBox<String> soundCombo = new JComboBox<>();
    private final JTextField defaultField = new JTextField();
    private final JTextField topField = new JTextField();
    private final JTextField bottomField = new JTextField();
    private final JTextField sideField = new JTextField();
    private final List<JCheckBox> typeCheckBoxes = new ArrayList<>();
    private final JButton reset = new JButton();
    private final JButton create = new JButton();
    private final UpdateListener validTextures = validateTextures();

    private final Color valid = Color.getHSBColor(152.79F, 61.43F, 54.9F);
    private final Color invalid = Color.getHSBColor(21.58F, 72.02F, 75.69F);

    private final Set<String> textures = new LinkedHashSet<>();

    private boolean nameIsValid = true;
    private boolean texturesAreValid = false;
    private boolean typesAreValid = false;

    public EditorPane(File root, String domain)
    {
        this.rootDir = Utils.getDir(createDirs, root, "assets", domain);
        this.domain = domain;

        JObject meta = new JObject()
                .add("pack", new JObject()
                        .add("pack_format", 1)
                        .add("description", "blockpack-dev"));

        Utils.toFile(meta.toPrettyJson(), new File(root, "pack.mcmeta"));
        definitionsRoot = Utils.getDir(createDirs, root, "assets", domain, "definitions");
        blockTextures = Utils.getDir(createDirs, root, "assets", domain, "textures", "blocks");
        Utils.getDir(createDirs, root, "assets", domain, "blockstates");
        Utils.getDir(createDirs, root, "assets", domain, "models", "block");
        Utils.getDir(createDirs, root, "assets", domain, "models", "item");

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setVisible(true);
        this.add(name());
        this.add(category());
        this.add(sounds());
        this.add(textures());
        this.add(types());
        this.add(buttons());

        Thread updater = new Thread(updater());
        updater.start();
    }

    private JPanel name()
    {
        JPanel namePane = new JPanel();
        JLabel nameLabel = new JLabel();
        nameLabel.setName("name");
        nameLabel.setText("name:");
        nameLabel.setPreferredSize(labelDims);
        nameField.setName("name");
        nameField.setForeground(valid);
        nameField.setPreferredSize(new Dimension(itemWidth, lineHeight));
        nameField.getDocument().addDocumentListener(validateName());
        namePane.add(nameLabel);
        namePane.add(nameField);
        return namePane;
    }

    private JPanel category()
    {
        JPanel categoryPane = new JPanel();
        JLabel categoryLabel = new JLabel();
        categoryLabel.setName("category");
        categoryLabel.setText("category:");
        categoryLabel.setPreferredSize(labelDims);
        categoryField.setName("category");
        categoryField.setForeground(valid);
        categoryField.setText("");
        categoryField.setPreferredSize(new Dimension(itemWidth, lineHeight));
        categoryPane.add(categoryLabel);
        categoryPane.add(categoryField);
        return categoryPane;
    }

    private JPanel sounds()
    {
        JPanel soundPane = new JPanel();
        JLabel soundLabel = new JLabel();
        soundLabel.setName("sound");
        soundLabel.setText("sound:");
        soundLabel.setPreferredSize(labelDims);
        soundCombo.setName("sound");
        soundCombo.setPreferredSize(new Dimension(itemWidth, lineHeight));
        MaterialSerializable.MATERIALS.forEach(soundCombo::addItem);
        soundCombo.setSelectedItem("STONE");
        soundPane.add(soundLabel);
        soundPane.add(soundCombo);
        return soundPane;
    }

    private JPanel textures()
    {
        Dimension inner = new Dimension(itemWidth - labelWidth - 10, lineHeight);

        JPanel texturePane = new JPanel();
        JLabel textureLabel = new JLabel();
        textureLabel.setName("textures");
        textureLabel.setText("textures:");
        textureLabel.setPreferredSize(labelDims);
        JPanel empty = new JPanel();
        empty.setPreferredSize(new Dimension(itemWidth, lineHeight));
        texturePane.add(textureLabel);
        texturePane.add(empty);

        JPanel textureOptionsPane = new JPanel();
        textureOptionsPane.setLayout(new BoxLayout(textureOptionsPane, BoxLayout.Y_AXIS));

        // #default
        JPanel defaultPane = new JPanel();
        JLabel defaultLabel = new JLabel();
        defaultLabel.setText("#default:");
        defaultLabel.setPreferredSize(labelDims);
        defaultField.setPreferredSize(inner);
        defaultField.setForeground(valid);
        defaultField.getDocument().addDocumentListener(validTextures);
        defaultField.getDocument().addDocumentListener(textureExists(defaultField));
        defaultPane.add(defaultLabel);
        defaultPane.add(defaultField);

        // #top
        JPanel topPane = new JPanel();
        JLabel topLabel = new JLabel();
        topLabel.setText("#top:");
        topLabel.setPreferredSize(labelDims);
        topField.setPreferredSize(inner);
        topField.setForeground(valid);
        topField.getDocument().addDocumentListener(validTextures);
        topField.getDocument().addDocumentListener(textureExists(topField));
        topPane.add(topLabel);
        topPane.add(topField);

        // #bottom
        JPanel bottomPane = new JPanel();
        JLabel bottomLabel = new JLabel();
        bottomLabel.setText("#bottom:");
        bottomLabel.setPreferredSize(labelDims);
        bottomField.setPreferredSize(inner);
        bottomField.setForeground(valid);
        bottomField.getDocument().addDocumentListener(validateTextures());
        bottomField.getDocument().addDocumentListener(textureExists(bottomField));
        bottomPane.add(bottomLabel);
        bottomPane.add(bottomField);

        // #side
        JPanel sidePane = new JPanel();
        JLabel sideLabel = new JLabel();
        sideLabel.setText("#side:");
        sideLabel.setPreferredSize(labelDims);
        sideField.setPreferredSize(inner);
        sideField.setForeground(valid);
        sideField.getDocument().addDocumentListener(validTextures);
        sideField.getDocument().addDocumentListener(textureExists(sideField));
        sidePane.add(sideLabel);
        sidePane.add(sideField);

        textureOptionsPane.add(defaultPane);
        textureOptionsPane.add(topPane);
        textureOptionsPane.add(bottomPane);
        textureOptionsPane.add(sidePane);

        this.add(texturePane);

        return textureOptionsPane;
    }

    private JPanel types()
    {
        for (BlockType type : BlockType.values())
        {
            JCheckBox box = new JCheckBox();
            box.setText(type.name());
            box.addActionListener(validateTypes(box));
            box.setForeground(valid);
            box.setPreferredSize(new Dimension(windowWidth / 3, lineHeight));
            typeCheckBoxes.add(box);
        }

        JPanel typesPane = new JPanel();

        JPanel labelPane = new JPanel();
        JLabel typesLabel = new JLabel();
        typesLabel.setName("types");
        typesLabel.setText("types:");
        typesLabel.setPreferredSize(labelDims);
        JPanel empty = new JPanel();
        empty.setPreferredSize(new Dimension(itemWidth, lineHeight));
        labelPane.add(typesLabel);
        labelPane.add(empty);

        int rows = (typeCheckBoxes.size() / 3) + (typeCheckBoxes.size() % 3);
        JPanel checksPane = new JPanel();
        checksPane.setLayout(new GridLayout(rows, 3));
        for (JCheckBox box : typeCheckBoxes)
        {
            box.setSelected(false);
            checksPane.add(box);
        }

        this.add(labelPane);
        typesPane.add(checksPane);

        return typesPane;
    }

    private JPanel buttons()
    {
        JPanel buttonsPane = new JPanel();

        reset.setText("Reset");
        reset.setPreferredSize(new Dimension(90 ,lineHeight));
        reset.addActionListener(reset());

        create.setText("Create");
        create.setEnabled(false);
        create.setPreferredSize(new Dimension(90 ,lineHeight));
        create.addActionListener(create());

        buttonsPane.add(reset);
        buttonsPane.add(create);

        return buttonsPane;
    }

    private boolean valid()
    {
        return texturesAreValid && nameIsValid && typesAreValid;
    }

    private UpdateListener validateName()
    {
        return new UpdateListener()
        {
            public void onUpdate()
            {
                if (nameField.getText().isEmpty())
                {
                    nameIsValid = false;
                    create.setEnabled(false);
                    return;
                }
                nameIsValid = true;
                create.setEnabled(valid());
            }
        };
    }

    private UpdateListener validateTextures()
    {
        return new UpdateListener()
        {
            public void onUpdate()
            {
                if (!defaultField.getText().isEmpty() || !topField.getText().isEmpty() && !bottomField.getText().isEmpty() && !sideField.getText().isEmpty())
                {
                    texturesAreValid = true;
                    create.setEnabled(valid());
                    return;
                }
                texturesAreValid = false;
                create.setEnabled(valid());
            }
        };
    }

    private UpdateListener textureExists(JTextField textField)
    {
        return new UpdateListener()
        {
            @Override
            public void onUpdate()
            {
                String texture = textField.getText().toLowerCase();
                textField.setForeground(textureExists(texture) ? valid : invalid);
            }
        };
    }

    private ActionListener validateTypes(JCheckBox box)
    {
        return e -> {
            if (e.getModifiers() > 16)
            {
                boolean setSelected = box.isSelected();
                for (JCheckBox box1 : typeCheckBoxes)
                {
                    box1.setSelected(setSelected);
                }
            }

            for (JCheckBox box1 : typeCheckBoxes)
            {
                if (box1.isSelected())
                {
                    typesAreValid = true;
                    create.setEnabled(valid());
                    return;
                }
            }
            typesAreValid = false;
            create.setEnabled(valid());
        };
    }

    private Runnable updater()
    {
        return () -> {
            while (true)
            {
                File[] files = blockTextures.listFiles();
                if (files != null)
                {
                    Set<String> found = new LinkedHashSet<>();
                    for (File file : files)
                    {
                        if (file.getName().endsWith(".png"))
                        {
                            found.add(file.getName().substring(0, file.getName().lastIndexOf(".")).toLowerCase());
                        }
                    }
                    updateTexturesList(found);

                    try
                    {
                        Thread.sleep(1000 * 5);
                    }
                    catch (InterruptedException e)
                    {
                        LogUtil.info("[ERROR] " + e.getMessage());
                        e.printStackTrace();
                    }
                }
            }
        };
    }

    private boolean textureExists(String  textureName)
    {
        synchronized (textures)
        {
            return textures.contains(textureName);
        }
    }

    private void updateTexturesList(Set<String> updates)
    {
        synchronized (textures)
        {
            textures.clear();
            textures.addAll(updates);
        }
    }

    private ActionListener reset()
    {
        return e -> {
            nameField.setText("");
            soundCombo.setSelectedIndex(0);
            defaultField.setText("");
            topField.setText("");
            bottomField.setText("");
            sideField.setText("");
            for (JCheckBox box : typeCheckBoxes)
            {
                box.setSelected(false);
            }
        };
    }

    private ActionListener create()
    {
        return e -> {

            try
            {
                String name = nameField.getText().toLowerCase().replace(" ", "_").replace("-", "_");

                DefinitionSerializable serializable = new DefinitionSerializable();
                serializable.name = name.toLowerCase();
                serializable.base = soundCombo.getSelectedItem().toString();
                List<String> types = typeCheckBoxes.stream().filter(AbstractButton::isSelected).map(JCheckBox::getText).collect(Collectors.toList());
                serializable.types = types.toArray(new String[types.size()]);
                serializable.file = serializable.name + ".json";

                File outDir = Utils.getDir(createDirs, definitionsRoot, categoryField.getText().toLowerCase().split("/"));
                File outFile = new File(outDir, serializable.file);
                Utils.toFile(serializable.toJson(), outFile);

                BlockTextures textures = new BlockTextures(defaultField.getText().toLowerCase(),
                        topField.getText().toLowerCase(),
                        bottomField.getText().toLowerCase(),
                        sideField.getText().toLowerCase());

                for (String s : serializable.types)
                {
                    Optional<BlockType> optional = BlockType.from(s);
                    if (optional.isPresent())
                    {
                        BlockType type = optional.get();
                        List<Conversion> conversions = Conversions.forType(type);
                        for (Conversion c : conversions)
                        {
                            c.writeStateFor(domain, type, rootDir, name);
                            c.writeModelsFor(domain, type, rootDir, name, textures);
                        }
                        continue;
                    }
                    LogUtil.blockpack(this, "Skipping unknown block type {} for legacy block state {}", s, name);
                }
            }
            catch (Throwable t)
            {
                LogUtil.info("[WARN] " + t.getMessage());
                t.printStackTrace();
            }
        };
    }
}
