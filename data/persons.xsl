<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
    <xsl:template match="/persons">
        <html xmlns="http://www.w3.org/1999/xhtml">
        <head>
            <title>Testing XML Example.</title>
        </head>
        <body>
            <h1>Persons</h1>
            <ul>
            <xsl:apply-templates select="person">
                <xsl:sort select="family_name" />
            </xsl:apply-templates>
            </ul>
        </body>
        </html>
    </xsl:template>
    <xsl:template match="person">
        <li>
            <xsl:value-of select="family_name"/>,
            <xsl:value-of select="name"/>
        </li>
    </xsl:template>
</xsl:stylesheet>