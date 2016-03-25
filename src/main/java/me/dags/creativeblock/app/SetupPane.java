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

import me.dags.creativeblock.app.util.UpdateListener;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class SetupPane extends JPanel
{
    private final JFrame parentFrame;
    private File targetDir = new File("");

    public SetupPane(JFrame parent)
    {
        File current = new File(this.getClass().getProtectionDomain().getCodeSource().getLocation().getFile());
        parentFrame = parent;
        targetDir = new File(current.getParentFile(), "blockpack-dev");

        final int windowWidth = 500;
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setVisible(true);

        try
        {
            BufferedImage image = ImageIO.read(this.getClass().getResource("/assets/creativeblock/cb.png"));
            JLabel icon = new JLabel(new ImageIcon(image));
            JPanel iconPane = new JPanel();
            iconPane.setPreferredSize(new Dimension(windowWidth, 170));
            iconPane.add(icon);

            this.add(iconPane);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        JLabel label = new JLabel();
        label.setText("Select a workspace:");
        label.setPreferredSize(new Dimension(150, 20));

        JPanel empty = new JPanel();
        empty.setPreferredSize(new Dimension(windowWidth - 150, 20));

        JPanel labelPane = new JPanel();
        labelPane.setMinimumSize(new Dimension(windowWidth, 20));
        labelPane.add(label);
        labelPane.add(empty);

        int chooseWidth = 80;
        JTextField path = new JTextField();
        path.setPreferredSize(new Dimension(windowWidth - chooseWidth * 2, 20));
        path.setName("path");
        path.setText(targetDir.getAbsolutePath());

        JButton choose = new JButton();
        choose.setName("choose");
        choose.setText("Choose");
        choose.addActionListener(this.fileExplorer(path));
        choose.setPreferredSize(new Dimension(chooseWidth, 20));

        JButton ok = new JButton();
        ok.setName("ok");
        ok.setText("Ok");
        ok.addActionListener(ok());
        ok.setPreferredSize(new Dimension(chooseWidth, 20));

        JPanel filesPane = new JPanel();
        filesPane.setMinimumSize(new Dimension(windowWidth, 40));
        filesPane.add(path);
        filesPane.add(choose);
        filesPane.add(ok);

        this.add(labelPane);
        this.add(filesPane);
    }

    public UpdateListener fileExplorer(JTextField pathField)
    {
        return new UpdateListener()
        {
            public void onUpdate()
            {
                JFileChooser dirChooser = new JFileChooser();
                dirChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                dirChooser.setFileHidingEnabled(false);
                dirChooser.ensureFileIsVisible(targetDir);
                dirChooser.setSelectedFile(targetDir);

                int response = dirChooser.showOpenDialog(SetupPane.this);
                switch (response)
                {
                    case JFileChooser.APPROVE_OPTION:
                        targetDir = dirChooser.getSelectedFile();
                        pathField.setText(targetDir.getAbsolutePath());
                        break;
                    default:
                        break;
                }
            }
        };
    }

    private UpdateListener ok()
    {
        return new UpdateListener()
        {
            public void onUpdate()
            {
                parentFrame.remove(SetupPane.this);
                // todo: make domain configurable - read mcmodinfo maybe?
                EditorPane editor = new EditorPane(targetDir, "acblocks");
                parentFrame.add(editor);
                parentFrame.pack();
                parentFrame.setVisible(true);
                parentFrame.setLocationRelativeTo(null);
            }
        };
    }
}
